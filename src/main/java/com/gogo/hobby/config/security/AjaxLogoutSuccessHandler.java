/**
 * AjaxLogoutSuccessHandler.java
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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: AjaxLogoutSuccessHandler
 * </p>
 * <p>
 * Description: 自定义注销成功处理器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus(200);
        responseBody.setMessage("注销成功!");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
