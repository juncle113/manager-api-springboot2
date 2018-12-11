package com.cpt.dapp.manager.api.auth.annotation;

import java.lang.annotation.*;

/**
 * 标注方法里需要填充的id参数
 *
 * @author sunli
 * @date 2018/12/07
 */
@Inherited
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentId {

}
