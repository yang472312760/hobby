/**
 * LogType.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.enums.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gogo.hobby.enums.IBaseEnum;

/**
 *
 * <p>
 * ClassName: LogType
 * </p>
 * <p>
 * Description: 日志类型
 * </p>
 * <p>
 * Author: yang
 * </p>
 * Date: 2019年7月11日
 * </p>
 * @author yang
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LogType implements IBaseEnum<Integer> {
    /**
     * 错误日志
     */
    ERRORLOG(1, "错误日志"),

    /**
     * 基础资料操作日志
     */
    BASICLOG(2, "基础资料操作日志"),

    /**
     * 业务操作日志
     */
    BUSINESSLOG(3, "业务操作日志"),

    /**
     * 系统资料操作日志
     */
    SYSLOG(4, "系统资料操作日志");

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
     * @param id id
     * @param displayName displayName
     */
    private LogType(int id, String displayName) {
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
     * 
     * <p>Description: 根据id获取枚举类</p>
     * @param id 根据id获取枚举类
     * @return LogType 日志类型
     */
    public static LogType getById(int id) {
        for (LogType tempEnum : values()) {
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
    }
}
