package com.cc.dapp.manager.api.auth.interceptor;

import com.cc.dapp.manager.api.auth.AuthManager;
import com.cc.dapp.manager.api.auth.annotation.Auth;
import com.cc.dapp.manager.api.enums.AdminRoleEnum;
import com.cc.dapp.manager.api.enums.AdminStatusEnum;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import com.cc.dapp.manager.api.model.domain.ManagerAdmin;
import com.cc.dapp.manager.api.repository.ManagerAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private AuthManager authManager;

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

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

        // 4.检查账号是否存在
        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(Integer.valueOf(id));
        if (!managerAdminOptional.isPresent()) {
            throw new AuthorizedException();
        }

        // 5.检查是否被禁用
        ManagerAdmin managerAdmin = managerAdminOptional.get();
        if (AdminStatusEnum.VALID.equals(managerAdmin.getStatus())) {
            throw new AuthorizedException();
        }

        // 6.检查可写权限（有权：系统管理员、超级管理员，无权：普通管理员）
        if (AuthManager.WRITE == auth.value()) {
            if (managerAdmin.getRoleType() != AdminRoleEnum.ROOT.getCode() &&
                    managerAdmin.getRoleType() != AdminRoleEnum.SUPER_ADMIN.getCode()) {
                throw new AuthorizedException();
            }
        }

        // 7.设置当前登录管理员id
        request.setAttribute(AuthManager.CURRENT_ID, Integer.valueOf(id));

        return true;
    }
}
