package com.zt.poseidon.common.search.solr;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/4
 */
@ConfigurationProperties(prefix = "spring.data.solr")
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


    public SolrProps() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getZkHost() {
        return zkHost;
    }

    public void setZkHost(String zkHost) {
        this.zkHost = zkHost;
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getDefaultMaxConnectionsPerHost() {
        return defaultMaxConnectionsPerHost;
    }

    public void setDefaultMaxConnectionsPerHost(Integer defaultMaxConnectionsPerHost) {
        this.defaultMaxConnectionsPerHost = defaultMaxConnectionsPerHost;
    }

    public Integer getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(Integer maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public Boolean getFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(Boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    public Boolean getAllowCompression() {
        return allowCompression;
    }

    public void setAllowCompression(Boolean allowCompression) {
        this.allowCompression = allowCompression;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SolrProps{");
        sb.append("host='").append(host).append('\'');
        sb.append(", zkHost='").append(zkHost).append('\'');
        sb.append(", soTimeout=").append(soTimeout);
        sb.append(", connectionTimeout=").append(connectionTimeout);
        sb.append(", defaultMaxConnectionsPerHost=").append(defaultMaxConnectionsPerHost);
        sb.append(", maxTotalConnections=").append(maxTotalConnections);
        sb.append(", followRedirects=").append(followRedirects);
        sb.append(", allowCompression=").append(allowCompression);
        sb.append('}');
        return sb.toString();
    }
}
