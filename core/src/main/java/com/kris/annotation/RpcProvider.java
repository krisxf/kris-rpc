package com.kris.annotation;


import java.lang.annotation.*;

/**
 * @Program: kris-rpc
 * @Description: 集合工具类
 * @Author: kris
 * @Create: 2025-03-06 20:57
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcProvider {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";
}
