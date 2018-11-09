package com.zt.poseidon.common.search.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description: elasticsearch 配置属性
 * @Date: 2018/8/24
 */
@ConfigurationProperties(prefix = "zt.es")
@Data
public class ElasticSearchProps {
    private String adresses;

    public String getAdresses() {
        return adresses;
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }
}
