/**
 * Role.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gogo.hobby.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * <p>
 * ClassName: Role
 * </p>
 * <p>
 * Description: 角色
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
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "")
public class Role extends BaseEntity {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 4207170667224570108L;

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色code
     */
    private String authority;

    /**
     * 角色类型
     */
    private Integer roleType;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 权限
     */
    @TableField(exist = false)
    private Set<Permission> permissions;

}
