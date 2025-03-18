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
@Target({ElementType.FIELD})
@Inherited
public @interface RpcConsumer {

    /**
     * 服务版本，默认值为空字符串
     */
    String version() default "";

    /**
     * 服务组，默认值为空字符串
     */
    String group() default "";
}
