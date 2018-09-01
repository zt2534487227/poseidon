package com.zt.poseidon.common.datasource;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DSType value() default DSType.WRITE;
}
