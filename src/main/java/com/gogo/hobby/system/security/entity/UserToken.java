/**
 * UserToken.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * <p>
 * ClassName: UserToken
 * </p>
 * <p>
 * Description: 用户
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken implements UserDetails {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 2521332477206009014L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否过期
     */
    private Boolean isAccountNonExpired;

    /**
     * 是够被锁定
     */
    private Boolean isAccountNonLocked;

    /**
     * 凭据是否过期
     */
    private Boolean isCredentialsNonExpired;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 是否记住我
     */
    private Boolean isRememberMe;

    /**
     * 用户的权限
     */
    private Set<RoleToken> authorities;

    /**
     * 
     * <p>
     * Description: 构造器
     * </p>
     * 
     * @param username 用户名
     * @param password 密码
     */
    public UserToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
