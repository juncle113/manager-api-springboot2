package com.cpt.dapp.manager.api.config;


import com.cpt.dapp.manager.api.auth.interceptor.AuthInterceptor;
import com.cpt.dapp.manager.api.auth.resolver.CurrentIdResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * web配置
 *
 * @author sunli
 * @date 2018/12/07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private CurrentIdResolver currentIdResolver;

    /**
     * 加载拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 加载鉴权
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }

    /**
     * 加载参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 加载当前用户id
        argumentResolvers.add(currentIdResolver);
    }

}
