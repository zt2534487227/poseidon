package com.zt.poseidon.common.distributedlock;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DistributedLockConfig.class)
@Documented
public @interface EnableDistributedLock {
}
