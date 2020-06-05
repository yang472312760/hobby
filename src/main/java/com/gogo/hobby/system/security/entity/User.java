/**
 * User.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gogo.hobby.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * <p>
 * ClassName: User
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户登陆
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String handImageUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否过期
     */
    private Integer isAccountNonExpired;

    /**
     * 是够被锁定
     */
    private Integer isAccountNonLocked;

    /**
     * 凭据是否过期
     */
    private Integer isCredentialsNonExpired;

    /**
     * 是否启用
     */
    private Integer isEnabled;

    /**
     * 是否记住我
     */
    private Integer isRememberMe;

}
