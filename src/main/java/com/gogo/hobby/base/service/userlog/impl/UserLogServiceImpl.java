/**
 * UserLogServiceImpl.java
 * Created at 2019-12-27
 * Created by libch
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.base.service.userlog.impl;

import com.gogo.hobby.base.service.userlog.IUserLogService;
import com.gogo.hobby.base.system.UserLog;
import com.gogo.hobby.util.JsonMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ClassName: UserLogServiceImpl
 * </p>
 * <p>
 * Description: 用户日志 服务类实现
 * </p>
 * <p>
 * Author: libch
 * </p>
 * <p>
 * Date: 2019-12-09
 * </p>
 *
 * @author libch
 */
@Service
@Slf4j
public class UserLogServiceImpl implements IUserLogService {

    /**
     * <p>
     * Description: 应用名称
     * </p>
     */
    @Value("${spring.application.name:e-matrix}")
    private String applicationName;

    /**
     * <p>Description: 解析日志内容</p>
     *
     * @param valueIsTemplate 是否是模板
     * @param value           日志内容
     * @param paramMap        参数集合
     * @param userLog         用户日志
     * @return UserLog 用户日志
     */
    @Override
    public UserLog analysisTemplate(boolean valueIsTemplate, String value, Map<String, Object> paramMap,
                                    UserLog userLog) {
        try {
            String appendContent = null;
            if (valueIsTemplate) {
                //是模板 value即为模板
                if (StringUtils.isNotBlank(value) && paramMap != null) {
                    Template template = new Template("name", new StringReader(value),
                            new Configuration(Configuration.VERSION_2_3_28));
                    StringWriter out = new StringWriter();
                    template.process(paramMap, out);
                    appendContent = out.toString();
                }
            } else {
                if (StringUtils.isNotBlank(value)) {
                    appendContent = value;
                }
            }
            if (appendContent != null) {
                userLog.setLogContent(appendContent);
            }
        } catch (Exception e) {
            log.error("日志线程SystemLogAspectThread报错：", e);
        }
        return userLog;
    }

    @Override
    public boolean sendUserLog(UserLog userLog) {
        boolean isOk = false;
        userLog.setApplicationName(this.applicationName);
        userLog.setCreateTime(new Date(System.currentTimeMillis()));
        String userLogToString = JsonMapper.toJsonString(userLog);
        try {
            //之前是用kafka发送消息，现在需要拆包，kafka的依赖拆分另一个包
            //以后需要兼容kafka,ribbitmq,mysql等的数据仓储
            //this.kafKaProducerService.send("log", this.applicationName, userLogToString);
            isOk = true;
        } catch (Exception e) {
            throw new RuntimeException("send userLog error", e);
        }
        return isOk;
    }

}
