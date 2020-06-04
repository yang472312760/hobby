/**
 * IUserLogService.java
 * Created at 2019-12-27
 * Created by libch
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.base.service.userlog;

import com.gogo.hobby.base.system.UserLog;
import java.util.Map;

/**
*
* <p>
* ClassName: IUserLogService
* </p>
* <p>
* Description: 用户日志 服务类
* </p>
* <p>
* Author: libch
* </p>
* <p>
* Date: 2019-12-09
* </p>
 * @author libch
*/
public interface IUserLogService {

    /**
     * <p>Description: 解析日志内容</p>
    * @param valueIsTemplate 是否是模板
     * @param value 日志内容
     * @param paramMap 参数集合
     * @param userLog 用户日志
     * @return UserLog 用户日志
     */
    UserLog analysisTemplate(boolean valueIsTemplate, String value, Map<String, Object> paramMap, UserLog userLog);

    /**
     * <p>Description: 发送用户日志</p>
     * @param userLogJson 用户日志Json
     * @return 用户日志集合
     */
    boolean sendUserLog(UserLog userLogJson);

}
