package com.tinklabs.b2c.cloud.parameterservice.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryCache {
    String value() default "";
}
