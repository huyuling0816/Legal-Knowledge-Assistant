package com.lkms.auth;

import com.lkms.enums.errorCode.impl.UserStatusCode;
import com.lkms.exception.ServiceException;
import com.lkms.utils.BeanCopierWithCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    JwtTokenService jwtTokenService;
    private JwtInfo jwtInfo;

    @Autowired
    public void setJwtInfo(JwtInfo jwtInfo) {
        this.jwtInfo = jwtInfo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;
        Authentication methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Authentication.class);
        if (methodAnnotation == null || !methodAnnotation.required()) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            jwtInfo.setNull();
            throw new ServiceException(UserStatusCode.TOKEN_INVALID);
        }
        Cookie token = new Cookie("token", "");
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie;
                break;
            }
        }
        if (token.getValue().equals("")) {
            jwtInfo.setNull();
            throw new ServiceException(UserStatusCode.TOKEN_INVALID);
        }
        JwtInfo infoFromToken = jwtTokenService.getInfoFromToken(token.getValue());
        if (infoFromToken.getId() == null) {
            jwtInfo.setNull();
            throw new ServiceException(UserStatusCode.TOKEN_INVALID);
        }
        BeanCopierWithCacheUtil.copy(infoFromToken, jwtInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (jwtInfo.getId() == null) return;
        Cookie token = new Cookie("token", jwtTokenService.generateToken(jwtInfo));
        token.setPath("/");
        token.setMaxAge(7200);
        response.addCookie(token);
    }

}
