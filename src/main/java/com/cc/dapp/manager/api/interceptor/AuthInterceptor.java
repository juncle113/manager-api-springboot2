package com.cc.dapp.manager.api.interceptor;

import com.cc.dapp.manager.api.annotation.Auth;
import com.cc.dapp.manager.api.constant.ProfileConstant;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (ProfileConstant.DEV.equals(profile)) {
            return true;
        }

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(!"token".equals(token)){
            throw new AuthorizedException();
        }

        return true;
    }
}
