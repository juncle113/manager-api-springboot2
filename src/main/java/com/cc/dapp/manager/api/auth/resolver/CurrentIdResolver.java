package com.cc.dapp.manager.api.auth.resolver;

import com.cc.dapp.manager.api.auth.AuthManager;
import com.cc.dapp.manager.api.auth.annotation.CurrentId;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Component
public class CurrentIdResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentId.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Integer currentId = (Integer) webRequest.getAttribute(AuthManager.CURRENT_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentId == null) {
            throw new MissingServletRequestPartException(AuthManager.CURRENT_ID);
        }
        return currentId;
    }
}
