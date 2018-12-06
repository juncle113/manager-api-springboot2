package com.cc.dapp.manager.api.auth.interceptor;

import com.cc.dapp.manager.api.auth.annotation.Auth;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import com.cc.dapp.manager.api.auth.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private AuthManager authManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        if (ProfileConstant.DEV.equals(profile)) {
//            return true;
//        }

        // 1.过滤拦截条件
        // 只拦截方法的场合
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 只拦截有权限检查注解的场合
        Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        // 2.取得相关信息
        // 取得token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            throw new AuthorizedException();
        }

        // 取得id
        String id = AuthManager.getIdByToken(token);
        if (id == null) {
            throw new AuthorizedException();
        }

        // 3.检查缓存的token是否一致
        String cacheToken = authManager.getToken(id);
        if (cacheToken == null || !cacheToken.equals(token)) {
            throw new AuthorizedException();
        }

        // 4.设置当前登录管理员id
        request.setAttribute(AuthManager.CURRENT_ID, Integer.valueOf(id));

        return true;
    }
}
