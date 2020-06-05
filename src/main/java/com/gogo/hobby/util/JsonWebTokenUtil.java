/**
 * JsonWebTokenUtil.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.util;

import com.gogo.hobby.system.security.entity.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * 
 * <p>
 * ClassName: JsonWebTokenUtil
 * </p>
 * <p>
 * Description: JWT工具类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
public class JsonWebTokenUtil {
    /**
     * token 头信息
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 用来进行jwt的签发和jwt的验证
     */
    private static final String SECRET = "bingo";
    /**
     * jwt签发者
     */
    private static final String ISS = "pms";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 
     * <p>
     * Description: 创建token
     * </p>
     * 
     * @param username username
     * @param isRememberMe isRememberMe
     * @return String String
     */
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET).setIssuer(ISS).setSubject(username)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 
     * <p>
     * Description: 创建Token
     * </p>
     * 
     * @param userDetails userDetails
     * @param isRememberMe isRememberMe
     * @return String String
     */
    public static String createToken(UserToken userDetails, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET).setIssuer(ISS)
                .setSubject(JsonMapper.toJsonString(userDetails)).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)).compact();
    }

    /**
     * 
     * <p>
     * Description: 从token中获取用户名
     * </p>
     * 
     * @param token token
     * @return String String
     */
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 
     * <p>Description: 从Token中获取用户信息</p>
     * @param token token
     * @return UserToken UserToken
     */
    public static UserToken getUser(String token) {
        return JsonMapper.fromJsonString(getTokenBody(token).getSubject(), UserToken.class);
    }

    /**
     * 
     * <p>Description: 是否已过期</p>
     * @param token token
     * @return boolean boolean
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }
    
    /**
     * 
     * <p>Description: 获取token</p>
     * @param token token
     * @return Claims Claims
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}
