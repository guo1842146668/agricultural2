package com.example.agricultural2.tool;

import com.example.agricultural2.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JavaWebToken {

    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bankgl");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    public static String createJavaWebToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map<String,Object> parserJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();

             return jwtClaims;

        } catch (Exception e) {
            return null;
        }
    }

    public static User TokenConvertUser(Map<String,Object> map) {
        User user = new User();
        if(map != null){
            Map<String,Object> user1 = (Map<String, Object>) map.get("user");
            if(user1 != null){
                if(user1.get("userId") != null){
                    user.setUserId(Integer.parseInt(user1.get("userId").toString()));
                }else{
                    user.setUserId(null);
                }
                if(user1.get("username") != null){
                    user.setUsername(user1.get("username").toString());
                }else{
                    user.setUsername(null);
                }
                if(user1.get("password") != null){
                    user.setPassword(user1.get("password").toString());
                }else{
                    user.setPassword(null);
                }
                if(user1.get("phone") != null){
                    user.setPhone(user1.get("phone").toString());
                }else{
                    user.setPhone(null);
                }
                if(user1.get("status") != null){
                    user.setStatus(Integer.parseInt(user1.get("status").toString()));
                }else{
                    user.setStatus(null);
                }
                if(user1.get("deptId") != null){
                    user.setDeptId(Integer.parseInt(user1.get("deptId").toString()));
                }else{
                    user.setDeptId(null);
                }
                if(user1.get("type") != null){
                    user.setType(Integer.parseInt(user1.get("type").toString()));
                }else{
                    user.setType(null);
                }
                return user;
            }
        }
        return user = null;
    }
}
