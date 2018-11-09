package com.zt.poseidon.common.search.solr;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/4
 */
@ConfigurationProperties(prefix = "spring.data.solr")
@Data
public class SolrProps {
    private String host = "http://127.0.0.1:8983/solr";
    private String zkHost;
    /**
     * socket read timeout
     */
    private Integer soTimeout=2000;
    private Integer connectionTimeout=1000;
    private Integer defaultMaxConnectionsPerHost=100;
    private Integer maxTotalConnections=2;
    private Boolean followRedirects=false;
    private Boolean allowCompression=true;


}
