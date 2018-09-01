package com.zt.poseidon.common.cache.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/21
 */
@ConfigurationProperties(
        prefix = "spring.redis"
)
public class RedispProps{
     private String host;
     private Integer port;
     private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RedispProps{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
