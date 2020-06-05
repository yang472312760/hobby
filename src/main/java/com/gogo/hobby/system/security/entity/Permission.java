/**
 * Permission.java
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
 * ClassName: 许可
 * </p>
 * <p>
 * Description: 许可
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
@TableName("sys_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission extends BaseEntity {
    
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
     * 许可名称
     */
    private String permissionName;

    /**
     * 许可类型
     */
    private Integer permissionType;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 类型
     */
    private String target;

    /**
     * 许可
     */
    private String permission;

}
