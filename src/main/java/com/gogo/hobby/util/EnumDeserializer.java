/**
 * EnumDeserializer.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gogo.hobby.enums.IBaseEnum;

import java.io.IOException;
import java.util.EnumSet;

/**
 * <p>
 * ClassName: EnumDeserializer
 * </p>
 * <p>
 * Description: 枚举反序列化
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019-08-27
 * </p>
 * @author yang
 */
@SuppressWarnings("rawtypes")
public class EnumDeserializer<E extends Enum<E> & IBaseEnum> extends JsonDeserializer<E> {

    @Override
    public E deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode value = jp.getCodec().readTree(jp);
        String literalName = null;
        JsonNode literalNameObj = value.get("literalName");
        if (literalNameObj != null) {
            literalName = literalNameObj.textValue();
        } else {
            literalName = value.textValue();
        }
        Class<?> type = null;
        try {
            type = Class.forName(getClassName(jp.getCurrentName()));
        } catch (ClassNotFoundException e) {
            throw new IOException("没有找到对应枚举类，请确认类名");
        }
        return toEnum(literalName, type);
    }

    /**
     * <p>
     * Description: 获取类路径
     * </p>
     * 
     * @param name 字段名
     * @return String 类路径
     */
    public String getClassName(String name) {
        return "com.gogo.hobby.enums.system." + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * 
     * <p>
     * Description: 获取枚举
     * </p>
     * 
     * @param literalName 名称
     * @param typeTemp 类型
     * @return E 泛型
     */
    @SuppressWarnings("unchecked")
    private E toEnum(String literalName, Class<?> typeTemp) {
        Class<E> type = (Class<E>) typeTemp;
        if (type != null && literalName != null) {
            EnumSet<E> set = EnumSet.allOf(type);
            if (set == null || set.size() <= 0) {
                return null;
            }
            for (E e : set) {
                if (literalName.equals(e.getLiteralName())) {
                    return e;
                }
            }
        }
        return null;
    }

}
