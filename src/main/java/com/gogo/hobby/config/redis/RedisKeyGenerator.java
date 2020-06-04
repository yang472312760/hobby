/**
 * RedisKeyGenerator.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.redis;

import com.gogo.hobby.util.ArrayUtils;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 *
 * <p>
 * ClassName: RedisKeyGenerator
 * </p>
 * <p>
 * Description: rediskey生成器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年9月28日
 * </p>
 * @author yang
 */
@Component("redisKeyGenerator")
public class RedisKeyGenerator implements KeyGenerator {

    /**
     * <p>
     * Description: NULL_PARAM_KEY
     * </p>
     */
    public static final int NULL_PARAM_KEY = 53;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        final StringBuilder STRING_BUILDER = new StringBuilder();
        String methodName = method.getName();
        STRING_BUILDER.append(methodName);
        if (params.length > 0) {
            handleParams(STRING_BUILDER, params);
        }
        return STRING_BUILDER;
    }

    /**
     * <p>Description: TODO</p>
     * @param stringBuilder stringBuilder
     * @param params params
     */
    public void handleParams(final StringBuilder stringBuilder, Object... params) {
        for (Object param : params) {
            if (param == null) {
                stringBuilder.append(NULL_PARAM_KEY).append("/");
                continue;
            }
            if (String.class.isAssignableFrom(param.getClass())
                    || Number.class.isAssignableFrom(param.getClass())) {
                stringBuilder.append(param).append("/");
                continue;
            }
            if (Date.class.isAssignableFrom(param.getClass())) {
                Date date = (Date) param;
                stringBuilder.append(date.getTime()).append("/");
                continue;
            }
            // list.toString是可行不妥的，不同机器，相同集合的地址不一样，降低缓存命中
            if (List.class.isAssignableFrom(param.getClass())) {
                stringBuilder.append(param.toString()).append("/");
                continue;
            }

            if (param.getClass().isArray()) {
                stringBuilder.append(ArrayUtils.objArrayToString(param)).append("/");
                continue;
            }

            stringBuilder.append(param).append("/");
        }
    }
}
