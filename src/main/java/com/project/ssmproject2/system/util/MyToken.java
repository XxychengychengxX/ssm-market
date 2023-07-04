/**
 * @author Valar Morghulis
 * @Date 2023/5/20
 */
package com.project.ssmproject2.system.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 生成密钥的工具类
 */
public class MyToken {

    public static String createJWT(Long id, String username, Integer userRole) {
        Date tokenExpireTime = MyDate.getTokenExpireTime();
        //添加主题，用户权限验证
        JwtBuilder jwtBuilder = Jwts.builder().setSubject("userRoleConfirm")
                .setIssuedAt(MyDate.getNowTimeInDate())
                .setExpiration(tokenExpireTime)
                .claim("id", id).claim("username", username).claim("userRole", userRole).signWith(SignatureAlgorithm.HS256, PublicArg.JWTSecretKey
                );
        String s = jwtBuilder.compact();
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    /**
     * 根据传入的jwt来解析用户名
     *
     * @param jwt 传入的jwt
     * @return 对应的用户名的字符串, 如果签名过期则返回空串
     */
    public static String parseJWTGetUsername(String jwt) {
        String s = jwt.substring(7);
        String decode = URLDecoder.decode(s, StandardCharsets.UTF_8);

        /*String decode = URLDecoder.decode(jwt, StandardCharsets.UTF_8);
        String s = decode.substring(7);*/
        Jws<Claims> claimsJws =
                null;
        try {
            claimsJws =
                    Jwts.parserBuilder().setSigningKey(PublicArg.JWTSecretKey).build().parseClaimsJws(decode);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
        Claims claims = claimsJws.getBody();
        //从jwtToken中解析出username
        return claims.get("username", String.class);


    }

    /**
     * 根据传入的jwt来解析用户id
     *
     * @param jwt 传入的jwt
     * @return 对应的用户名的字符串
     */
    public static Long parseJWTGetUserID(String jwt) {
        String s = jwt.substring(7);
        String decode = URLDecoder.decode(s, StandardCharsets.UTF_8);
        //进行用户名的解析
        //开始解析jwt对象

        Jws<Claims> claimsJws =
                null;
        try {
            claimsJws =
                    Jwts.parserBuilder().setSigningKey(PublicArg.JWTSecretKey).build().parseClaimsJws(decode);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
        Claims claims = claimsJws.getBody();
        //从jwtToken中解析出username
        return claims.get("id", Long.class);

    }

    /**
     * 根据传入的jwt来解析用户身份
     *
     * @param jwt 传入的jwt
     * @return 对应的用户名的字符串
     */
    public static int parseJWTGetUserRole(String jwt) {
        String s = jwt.substring(7);
        String decode = URLDecoder.decode(s, StandardCharsets.UTF_8);

        //进行用户名的解析
        //开始解析jwt对象
        Jws<Claims> claimsJws =
                null;
        try {
            claimsJws =
                    Jwts.parserBuilder().setSigningKey(PublicArg.JWTSecretKey).build().parseClaimsJws(decode);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
        Claims claims = claimsJws.getBody();
        //从jwtToken中解析出username
        return claims.get("userRole", Integer.class);


    }
}
