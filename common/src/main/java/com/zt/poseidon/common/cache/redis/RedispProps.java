package com.zt.poseidon.common.cache.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/21
 */
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedispProps{
     private String host;
     private Integer port;
     private String password;

}
