package com.gogo.hobby.annotation;

import com.gogo.hobby.enums.system.LogType;
import com.gogo.hobby.enums.system.OperateType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@ProjectName:bingo-framework-base</p>
 * <p>@Package:com.bingo.framework.base.annotation</p>
 * <p>@ClassName:SystemLog</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/4/1 13:31</p>
 * <p>@Version:1.0</p>
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * <p>
     * Description: 设置日志类型
     *
     * @return LogType
     * </p>
     */
    LogType logType() default LogType.BUSINESSLOG;

    /**
     * <p>
     * Description: 操作日志类型
     *
     * @return OperateType
     * </p>
     */
    OperateType operateType() default OperateType.SELECT;

    /**
     * <p>
     * Description: 值是否是freemaker模板,默认为否
     *
     * @return boolean
     * </p>
     */
    boolean valueIsTemplate() default false;

    /**
     * <p>
     * Description: 设置日志内容
     *
     * @return String
     * </p>
     */
    String logContent() default "";

    /**
     * <p>
     * Description: 模块名称
     *
     * @return String
     * </p>
     */
    String modelName() default "";

}
