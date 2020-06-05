/**
 * Md5Util.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.util;

import java.security.MessageDigest;

/**
 * 
 * <p>ClassName: Md5Util</p>
 * <p>Description: MD5加密工具类</p>
 * <p>Author: yang</p>
 * <p>Date: 2019年7月31日</p>
 */
public class Md5Util {

    /**
     * 盐
     */
    private static final String SALT = "bingo";

    
    /**
     * 
     * <p>Description: 加密</p>
     * @param password password
     * @return String String
     */
    public static String encode(String password) {
        password = password + SALT;
        return processEncode(password);
    }

    /**
     * 
     * <p>Description: 编码</p>
     * @param password password
     * @return String String
     */
    public static String processEncode(String password) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        char[] charArray = password.toCharArray();

        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
