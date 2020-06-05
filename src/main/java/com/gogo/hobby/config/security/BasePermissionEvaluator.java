/**
 * BasePermissionEvaluator.java
 * Created at 2019-7-30
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.security;

import com.gogo.hobby.system.security.entity.PermissionToken;
import com.gogo.hobby.system.security.entity.RoleToken;
import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: BasePermissionEvaluator
 * </p>
 * <p>
 * Description: 自定义权限验证 用于注解hasPermission处理请求
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 * @author Administrator
 */
@Component
public class BasePermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        boolean a = false;
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        } else {
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                for (PermissionToken o : ((RoleToken) grantedAuthority).getPermissions()) {
                    if (o.getTarget().equals(target)) {
                        if (o.getPermission().equals(permission)) {
                            return true;
                        }
                    }
                }
            }
        }
        return a;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String string, Object object) {
        throw new RuntimeException("Id-based permission evaluation not currently supported.");
    }
}
