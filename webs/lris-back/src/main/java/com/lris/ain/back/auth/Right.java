package com.lris.ain.back.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Right {
	
	public abstract String key();  // 操作key

	public abstract String name(); // 操作名称
}
