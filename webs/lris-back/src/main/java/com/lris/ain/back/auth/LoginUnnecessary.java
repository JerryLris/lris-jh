package com.lris.ain.back.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要检查登录时用此注解
 *
 * @author 李文竣
 */
@Documented
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LoginUnnecessary {

}
