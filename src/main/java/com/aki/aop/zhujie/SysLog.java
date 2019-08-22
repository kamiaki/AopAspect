package com.aki.aop.zhujie;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    //打印注解上的描述
    String value() default "";
}
