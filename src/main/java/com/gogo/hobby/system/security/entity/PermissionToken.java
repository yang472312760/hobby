/**
 * PermissionToken.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * <p>
 * ClassName: PermissionToken
 * </p>
 * <p>
 * Description: PermissionToken
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
public class PermissionToken implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -1055786466960046446L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 类型
     */
    private String target;

    /**
     * 许可
     */
    private String permission;

}
