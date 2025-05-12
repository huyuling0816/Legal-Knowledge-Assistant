package com.lkms.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtUtil {
    private Key getKey(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //TODO 使用环境变量而不是把 secret 硬编码在这里
        String secret = DatatypeConverter.printBase64Binary("todo".getBytes());
        return new SecretKeySpec(secret.getBytes(), signatureAlgorithm.getJcaName());
    }

    public String generateToken(JwtInfo jwtInfo, int expire) {
        return Jwts.builder().claim(JwtConst.JWT_KEY_UID, jwtInfo.getId())
                .claim(JwtConst.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.HS256, getKey()).compact();
    }

    public JwtInfo getTokenInfo(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return JwtInfo.builder()
                .id(claims.get(JwtConst.JWT_KEY_UID, Integer.class))
                .name(claims.get(JwtConst.JWT_KEY_NAME, String.class))
                .build();
    }
}
