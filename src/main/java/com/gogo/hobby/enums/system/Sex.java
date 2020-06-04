package com.gogo.hobby.enums.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gogo.hobby.enums.IBaseEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Sex implements IBaseEnum<Integer> {

    /**
     * 修改
     */
    MAN(1, "男"),

    /**
     * 删除
     */
    WOMAN(2, "女");

    /**
     * id
     */
    private int id;

    /**
     * 描述
     */
    private String displayName;


    private Sex(int id, String displayName) {
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
    public static Sex getById(int id) {
        for (Sex tempEnum : values()) {
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
