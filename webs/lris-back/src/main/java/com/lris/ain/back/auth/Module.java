package com.lris.ain.back.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Module {
	
	public abstract String pkey(); //模块key
	
	public abstract String key();  //菜单key

	public abstract String name(); //菜单名称

}
