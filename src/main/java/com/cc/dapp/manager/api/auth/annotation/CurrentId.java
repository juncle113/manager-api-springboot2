package com.cc.dapp.manager.api.auth.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentId {

}
