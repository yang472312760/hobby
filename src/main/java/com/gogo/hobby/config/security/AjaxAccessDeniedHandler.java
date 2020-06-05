/**
 * AjaxAccessDeniedHandler.java
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: AjaxAccessDeniedHandler
 * </p>
 * <p>
 * Description: 自定义权限不足处理器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus(401);
        responseBody.setMessage("权限不足，请联系管理员!");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
