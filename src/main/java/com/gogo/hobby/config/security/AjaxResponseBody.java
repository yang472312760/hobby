/**
 * AjaxLogoutSuccessHandler.java
 * Created at 2019-7-30
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * <p>
 * ClassName: AjaxResponseBody
 * </p>
 * <p>
 * Description: 返回体
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResponseBody {

    /**
     * 返回状态码
     */
    private Integer status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回结果
     */
    private Object result;

}
