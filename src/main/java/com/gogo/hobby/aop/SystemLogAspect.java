package com.gogo.hobby.aop;

import com.gogo.hobby.annotation.SystemLog;
import com.gogo.hobby.base.service.userlog.IUserLogService;
import com.gogo.hobby.base.system.CommonResult;
import com.gogo.hobby.base.system.UserLog;
import com.gogo.hobby.enums.system.LogType;
import com.gogo.hobby.enums.system.OperateType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * <p>@ProjectName:bingo-framework-base</p>
 * <p>@Package:com.bingo.framework.base.aop</p>
 * <p>@ClassName:SystemLogAspect</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/4/3 10:12</p>
 * <p>@Version:1.0</p>
 */
@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${hobby.log.enabled}")
public class SystemLogAspect {

    /**
     * <p>
     * Description: 参数变量
     * </p>
     */
    public static final String PRE_OPERATOR_OF_METHOD_KEY = "param";

    /**
     * <p>
     * Description: 应用名称
     * </p>
     */
    @Value("${spring.application.name:bingo-framework}")
    private String applicationName;

    /**
     * <p>Description: 用户日志服务</p>
     */
    @Autowired
    private IUserLogService iUserLogService;

    @AfterReturning(pointcut = "execution (* com.gogo.hobby..controller.*Controller.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, final CommonResult result) {
        SystemLog systemLog = getAnnotationFromJoinPoint(joinPoint);
        if (systemLog != null) {
            UserLog userLog = getUserLog(joinPoint, result, systemLog);
            //目前没有具体实现，后期需要改
            this.iUserLogService.sendUserLog(userLog);
        }
    }

    private UserLog getUserLog(JoinPoint joinPoint, Object result, SystemLog systemLog) {
        UserLog userLog = new UserLog();
        //类名
        String className = joinPoint.getSignature().getDeclaringTypeName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        //日志类型
        LogType logType = systemLog.logType();
        //操作类型
        OperateType operateType = systemLog.operateType();
        //值是否是freemaker模板
        boolean valueIsTemplate = systemLog.valueIsTemplate();

        String value = systemLog.logContent();

        Object[] args = joinPoint.getArgs();

        //存放参数
        Map<String, Object> paramMap = new HashMap<String, Object>(16);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                paramMap.put(PRE_OPERATOR_OF_METHOD_KEY + i, args[i]);
            }
            if (result != null) {
                //把方法执行结果也放入
                paramMap.put(PRE_OPERATOR_OF_METHOD_KEY + args.length, result);
            }
            userLog = this.iUserLogService.analysisTemplate(valueIsTemplate, value, paramMap, userLog);
        }

        userLog.setOperateType(operateType.getDisplayName());
        userLog.setLogType(logType.getDisplayName());
        userLog.setApplicationName(this.applicationName);
        userLog.setCreateTime(new Date(System.currentTimeMillis()));
        userLog.setClassName(className);
        userLog.setMethodName(methodName);
        UserLog setUser = setUser(userLog);
        return setUser;
    }

    /**
     * <p>
     * Description: 设置用户属性(以后做oauth2.0认证时需要改)
     * </p>
     *
     * @param userLog 用户日志
     * @return Userlog 用户日志
     */
    UserLog setUser(UserLog userLog) {
        userLog.setUserId(12);
        userLog.setUserName("admin");
        //userLog.setUserName(UserContext.getPernr());
        //userLog.setUserId(UserContext.getId());
        userLog.setUserIp("127.0.0.1");
        return userLog;
    }

    private SystemLog getAnnotationFromJoinPoint(JoinPoint joinPoint) {
        Method method = invocationMethod(joinPoint);
        if (method != null && method.isAnnotationPresent(SystemLog.class)) {
            return method.getAnnotation(SystemLog.class);
        }
        return null;
    }

    private Method invocationMethod(JoinPoint joinPoint) {
        try {
            Field methodInvocationField = MethodInvocationProceedingJoinPoint.class
                    .getDeclaredField("methodInvocation");
            methodInvocationField.setAccessible(true);
            ProxyMethodInvocation methodInvocation = (ProxyMethodInvocation) methodInvocationField.get(joinPoint);
            return methodInvocation.getMethod();
        } catch (NoSuchFieldException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}
