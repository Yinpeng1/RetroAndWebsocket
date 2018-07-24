package com.yp.demo.annotation;

import org.apache.commons.lang.StringUtils;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {

    /**
     * 对日志的描述
     * @return
     */
    String description() default StringUtils.EMPTY;
}
