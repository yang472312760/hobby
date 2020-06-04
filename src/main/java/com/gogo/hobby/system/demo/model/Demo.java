/**
 * Demo.java
 * Created at 2020-06-04
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.demo.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gogo.hobby.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gogo.hobby.enums.system.Sex;
import com.gogo.hobby.util.EnumDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ClassName: Demo
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2020-06-04
 * </p>
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@TableName("sys_demo")
@ApiModel(value = "Demo对象", description = "")
public class Demo{

    /**
     * <p>Field serialVersionUID: 序列化</p>
     */
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @EnumValue
    @JsonDeserialize(using = EnumDeserializer.class)
    private Sex sex;



}
