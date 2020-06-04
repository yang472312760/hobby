/**
 * ArrayUtils.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.util;

import java.util.Arrays;

/**
 * 
 * <p>
 * ClassName: ArrayUtils
 * </p>
 * <p>
 * Description: 数组工具类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年9月28日
 * </p>
 * @author yang
 */
public class ArrayUtils {

    /**
     * 
     * <p>
     * Description: obj是否是数组
     * </p>
     * 
     * @param obj 数组对象
     * @return boolean true/false
     */
    public static boolean isArray(Object obj) {
        if (obj != null) {
            return obj.getClass().isArray();
        }
        return false;
    }

    /**
     * 
     * <p>
     * Description: obj(实际是数组)转为字符串
     * </p>
     * 
     * @param obj 数组对象
     * @return String 字符串
     */
    public static String objArrayToString(Object obj) {
        String s = null;
        if (isArray(obj)) {
            Class<? extends Object> eClass = obj.getClass();
            if (eClass == byte[].class) {
                s = Arrays.toString((byte[]) obj);
            } else if (eClass == short[].class) {
                s = Arrays.toString((short[]) obj);
            } else if (eClass == int[].class) {
                s = Arrays.toString((int[]) obj);
            } else if (eClass == long[].class) {
                s = Arrays.toString((long[]) obj);
            } else if (eClass == char[].class) {
                s = Arrays.toString((char[]) obj);
            } else if (eClass == float[].class) {
                s = Arrays.toString((float[]) obj);
            } else if (eClass == double[].class) {
                s = Arrays.toString((double[]) obj);
            } else if (eClass == boolean[].class) {
                s = Arrays.toString((boolean[]) obj);
            } else {
                s = Arrays.toString((Object[]) obj);
            }
        }
        return s;
    }

}
