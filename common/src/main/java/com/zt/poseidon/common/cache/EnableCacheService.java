package com.zt.poseidon.common.cache;

import com.zt.poseidon.common.cache.redis.RedisConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/21
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CacheRegister.class)
@Documented
public @interface EnableCacheService {
    Class register() default RedisConfig.class;
}
