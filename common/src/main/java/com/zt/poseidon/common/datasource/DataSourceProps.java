package com.zt.poseidon.common.datasource;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.Map;


/**
 * @Author: zhoutian
 * @Description: 多数据源配置属性
 * @Date: 2018/7/10
 */
@ConfigurationProperties(prefix = "zt.datasource")
@Data
public class DataSourceProps {
    /**
     * 数据源类型
     */
    private Class<? extends DataSource> type;
    /**
     * jdbc驱动加载类
     */
    private String driverClassName;
    /**
     * *mapper.xml扫描路径
     */
    private String mapperLocations="classpath:mapping/*.xml";
    /**
     * dao层扫描路径
     */
    private String daoPackages;
    /**
     * entity 层扫描路径
     */
    private String entityPackages;
    private Integer initialSize=20;
    private Integer maxActive=40;
    private Integer minIdle=5;
    private boolean defaultAutoCommit=true;
    private boolean testOnBorrow=true;
    private String validationQuery="SELECT 1";
    private String url;
    private String username;
    private String password;
    /**
     * 是否启用性能分析插件
     */
    private boolean usePerformanceInterceptor=false;
    /**
     * 从数据源个数,默认2
     */
    private Integer slaveNums=2;
    /**
     * 是否开启主从数据源
     */
    private boolean masterSlave;
    private Map<String,MyDataSource> slaves;
    private String txAopExpression;


    @Data
    public static class MyDataSource{
        private String url;
        private String username;
        private String password;



    }

}
