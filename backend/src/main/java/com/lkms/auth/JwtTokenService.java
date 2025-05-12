package com.lkms.auth;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JwtTokenService {

    @Resource
    JwtUtil jwtUtil;

    private final int EXPIRE = 7200; // 设置 token 过期时间

    public String generateToken(JwtInfo jwtInfo) {
        return jwtUtil.generateToken(jwtInfo, EXPIRE);
    }

    public JwtInfo getInfoFromToken(String token) {
        return jwtUtil.getTokenInfo(token);
    }

}
