package com.cc.dapp.manager.api.auth.annotation;

import com.cc.dapp.manager.api.auth.AuthManager;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    int value() default AuthManager.WRITE;
}
