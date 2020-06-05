/**
 * AjaxAuthenticationEntryPoint.java
 * Created at 2019-7-30
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.security;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: AjaxAuthenticationEntryPoint
 * </p>
 * <p>
 * Description: 验证
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus(500);
        responseBody.setMessage("请先登录!");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));

    }
}
