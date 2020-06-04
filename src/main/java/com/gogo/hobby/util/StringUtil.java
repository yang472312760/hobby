/**
 * StringUtil.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.util;

import com.gogo.hobby.constant.BaseConstants;

/**
 * <p>
 * ClassName: StringUtil
 * </p>
 * <p>
 * Description: 驼峰工具类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月11日
 * </p>
 *
 * @author yang
 */
public class StringUtil {

    /**
     * <p>
     * Description: 下划线命名转为驼峰命名
     * </p>
     *
     * @param para 下划线命名的字符串
     * @return java.lang.String 驼峰命名
     */
    public static String uderlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] a = para.split(BaseConstants.THE_UNDERLINE);
        for (String s : a) {
            if (!para.contains(BaseConstants.THE_UNDERLINE)) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * <p>
     * Description: 驼峰命名转为下划线命名
     * </p>
     *
     * @param para 驼峰命名的字符串
     * @return java.lang.String 下划线命名
     */
    public static String umpToUnderline(String para) {

        StringBuilder sb = new StringBuilder(para);
        //定位
        int temp = 0;
        if (!para.contains(BaseConstants.THE_UNDERLINE)) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

}
