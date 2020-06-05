/**
 * RoleToken.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.entity;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * <p>
 * ClassName: RoleToken
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
@NoArgsConstructor
@AllArgsConstructor
public class RoleToken implements GrantedAuthority {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -4558551490214914842L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色
     */
    private String authority;

    /**
     * 许可
     */
    private Set<PermissionToken> permissions;

}
