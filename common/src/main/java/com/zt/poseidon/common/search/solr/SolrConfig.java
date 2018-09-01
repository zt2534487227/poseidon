package com.zt.poseidon.common.search.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.SolrJConverter;
import org.springframework.data.solr.server.SolrClientFactory;
import org.springframework.data.solr.server.support.HttpSolrClientFactory;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/4
 */
@Configuration
@EnableConfigurationProperties(SolrProps.class)
public class SolrConfig implements EnvironmentAware {

    private SolrProps solrProps;

    @Override
    public void setEnvironment(Environment environment) {
        if (this.solrProps ==null){
            solrProps= Binder.get(environment).bind(SolrProps.class.getAnnotation(ConfigurationProperties.class).prefix()
                    , SolrProps.class).get();
        }
    }

    @Bean
    public SolrClientFactory solrClientFactory(){
        SolrClientFactory solrClientFactory=new HttpSolrClientFactory(solrClient());
        return solrClientFactory;
    }


    @Bean
    public SolrClient solrClient(){
        HttpSolrClient httpSolrClient=new HttpSolrClient.Builder(solrProps.getHost()).build();
        httpSolrClient.setSoTimeout(solrProps.getSoTimeout()); // socket read timeout
        httpSolrClient.setConnectionTimeout(solrProps.getConnectionTimeout());
        httpSolrClient.setDefaultMaxConnectionsPerHost(solrProps.getDefaultMaxConnectionsPerHost());
        httpSolrClient.setMaxTotalConnections(solrProps.getMaxTotalConnections());
        httpSolrClient.setFollowRedirects(solrProps.getFollowRedirects()); // defaults to false
        httpSolrClient.setAllowCompression(solrProps.getAllowCompression());
        return httpSolrClient;
    }

    @Bean
    public SolrTemplate solrTemplate(){
        SolrTemplate solrTemplate=new SolrTemplate(solrClient());
        solrTemplate.setSolrConverter(new SolrJConverter());
        return solrTemplate;
    }

    @Bean SolrManage solrManage(){
        SolrManage solrManage=new SolrManage(solrClient());
        return  solrManage;
    }
}
