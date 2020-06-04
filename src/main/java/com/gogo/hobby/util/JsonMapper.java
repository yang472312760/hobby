/**
 * JsonMapper.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import java.io.IOException;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>
 * ClassName: JsonMapper
 * </p>
 * <p>
 * Description: 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 * @author yang
 */
@Slf4j
@SuppressWarnings("deprecation")
public class JsonMapper extends ObjectMapper {

    /**
     * <p>
     * Field serialVersionUID: 序列化
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Description: mapper
     * </p>
     */
    private static JsonMapper mapper;

    /**
     * 
     * <p>
     * Description: 构造器
     * </p>
     */
    public JsonMapper() {
        this(JsonInclude.Include.ALWAYS);
    }

    /**
     * 
     * <p>
     * Description: 构造器
     * </p>
     * 
     * @param include include
     */
    public JsonMapper(JsonInclude.Include include) {
        // 设置输出时包含属性的风格
        if (include != null) {
            this.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) {
                try {
                    jgen.writeString("");
                } catch (IOException e) {
                    log.info(e.getMessage(), e);
                }
            }
        });
        // 进行HTML解码。
        this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>() {
            @Override
            public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) {
                try {
                    jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }));
        // 设置时区
        //getTimeZone("GMT+8:00")
        this.setTimeZone(TimeZone.getDefault());
    }

    /**
     * 
     * <p>
     * Description:
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     * </p>
     * 
     * @return JsonMapper JsonMapper
     */
    public static JsonMapper getInstance() {
        if (mapper == null) {
            mapper = new JsonMapper().enableSimple();
        }
        return mapper;
    }

    /**
     * 
     * <p>
     * Description: 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     * </p>
     * 
     * @return JsonMapper JsonMapper
     */
    public static JsonMapper nonDefaultMapper() {
        if (mapper == null) {
            mapper = new JsonMapper(JsonInclude.Include.NON_DEFAULT);
        }
        return mapper;
    }

    /**
     * 
     * <p>
     * Description: Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     * </p>
     * 
     * @param object 对象
     * @return String 字符串
     */
    public String toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("write to json string error:" + object, e);
            }
            return null;
        }
    }

    /**
     * 
     * <p>
     * Description: 反序列化POJO或简单Collection如List
     * </p>
     * 
     * @param jsonString 字符串
     * @param clazz 类型
     * @param <T> 泛型
     * @return T 泛型
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return this.readValue(jsonString, clazz);
        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("parse json string error:" + jsonString != null ? jsonString.replaceAll("[\r\n]", "") : "", e);
            }
            return null;
        }
    }

    /**
     * 
     * <p>
     * Description: 反序列化复杂Collection如List<Bean>,
     * 先使用函數createCollectionType构造类型,然后调用本函数.
     * </p>
     * 
     * @param jsonString jsonString
     * @param javaType javaType
     * @param <T> javaType
     * @return T 泛型
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) this.readValue(jsonString, javaType);
        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("parse json string error:" + jsonString, e);
            }
            return null;
        }
    }

    /**
     * 
     * <p>
     * Description: 構造泛型的Collection Type如: ArrayList
     * </p>
     * 
     * @param collectionClass 类型
     * @param elementClasses 元素
     * @return JavaType javaType
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 
     * <p>
     * Description: 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
     * </p>
     * 
     * @param jsonString jsonString
     * @param object object
     * @param <T> object
     * @return T t
     */
    @SuppressWarnings("unchecked")
    public <T> T update(String jsonString, T object) {
        try {
            return (T) this.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            if (log.isWarnEnabled()) {
                log.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
            }
        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
            }
        }
        return null;
    }

    /**
     * 
     * <p>
     * Description: 輸出JSONP格式數據.
     * </p>
     * 
     * @param functionName functionName
     * @param object object
     * @return String String
     */
    public String toJsonpObject(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 
     * <p>
     * Description: 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * </p>
     * 
     * @return JsonMapper JsonMapper
     */
    public JsonMapper enableEnumUseToString() {
        this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return this;
    }

    /**
     * 
     * <p>
     * Description: 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
     * </p>
     * 
     * @return JsonMapper JsonMapper
     */
    public JsonMapper enableJaxbAnnotation() {
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        this.registerModule(module);
        return this;
    }

    /**
     * 
     * <p>
     * Description: 允许单引号 允许不带引号的字段名称
     * </p>
     * 
     * @return JsonMapper JsonMapper
     */
    public JsonMapper enableSimple() {
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    /**
     * 
     * <p>
     * Description: 取出Mapper做进一步的设置或使用其他序列化API.
     * </p>
     * 
     * @return ObjectMapper ObjectMapper
     */
    public ObjectMapper getMapper() {
        return this;
    }

    /**
     * 
     * <p>
     * Description: 对象转换为JSON字符串
     * </p>
     * 
     * @param object object
     * @return String String
     */
    public static String toJsonString(Object object) {
        return JsonMapper.getInstance().toJson(object);
    }

    /**
     * 
     * <p>
     * Description: JSON字符串转换为对象
     * </p>
     * 
     * @param jsonString jsonString
     * @param clazz clazz
     * @param <T> <t>
     * @return T T
     */
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        return JsonMapper.getInstance().fromJson(jsonString, clazz);
    }

    /**
     * 
     * <p>
     * Description: 将obj对象转换成 class类型的对象
     * </p>
     * 
     * @param obj 转换对象
     * @param clazz class类型的对象
     * @param <T> class类型的对象
     * @return <T> 泛型
     */
    public static <T> T parseObject(Object obj, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(obj), clazz);
    }

}
