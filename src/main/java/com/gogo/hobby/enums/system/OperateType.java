/**
 * OperateType.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.enums.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gogo.hobby.enums.IBaseEnum;

/**
 * <p>
 * ClassName: OperateType
 * </p>
 * <p>
 * Description: 操作类型
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年9月29日
 * </p>
 *
 * @author yang
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OperateType implements IBaseEnum<Integer> {

    /**
     * 查询
     */
    SELECT(1, "查询"),

    /**
     * 添加
     */
    INSERT(2, "添加"),

    /**
     * 修改
     */
    UPDATE(3, "修改"),

    /**
     * 删除
     */
    DELETE(4, "删除");

    /**
     * id
     */
    private int id;

    /**
     * 描述
     */
    private String displayName;

    /**
     * 构造器
     *
     * @param id          id
     * @param displayName displayName
     */
    private OperateType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * <p>Description: 根据id获取枚举类</p>
     *
     * @param id 根据id获取枚举类
     * @return LogType 日志类型
     */
    public static OperateType getById(int id) {
        for (OperateType tempEnum : values()) {
            if (tempEnum.getId() == id) {
                return tempEnum;
            }
        }
        return null;
    }

    @Override
    public Integer getIdentity() {
        return this.id;
    }

    @Override
    public String getLiteralName() {
        return this.toString();
    }}
