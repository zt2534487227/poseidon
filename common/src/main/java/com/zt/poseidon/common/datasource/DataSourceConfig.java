package com.zt.poseidon.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Lists;
import org.aopalliance.aop.Advice;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(DataSourceProps.class)
@EnableTransactionManagement
@MapperScan(basePackages="${zt.datasource.dao-packages}")
public class DataSourceConfig implements EnvironmentAware{

    private DataSourceProps dataSourceProps;

    public void setEnvironment(Environment environment) {
        if (this.dataSourceProps ==null){
            dataSourceProps= Binder.get(environment).bind(DataSourceProps.class.getAnnotation(ConfigurationProperties.class).prefix()
                    , DataSourceProps.class).get();
        }
    }

    // 数据源
    private DataSource masterDS;
    private Map<String, DataSource> SlavesDS = new HashMap<String, DataSource>();


    /**
     * 多数据源配置aop读写分离
     */
    @Configuration
    @ConditionalOnProperty(name = "zt.datasource.master-slave",havingValue = "true")
    class MasterSlaveConfig{

        @Bean
        public Advice dsAdvice(){
            DynamicDataSourceAdvice dataSourceAdvice=new DynamicDataSourceAdvice();
            dataSourceAdvice.setDynamicDataSource((DynamicDataSource) dataSourceProxy());
            return dataSourceAdvice;
        }

        @Bean
        public Advisor dsAdvisor(){
            String expression = dataSourceProps.getTxAopExpression();
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            String genericExpression="execution(* com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.*(..))";
            if (!StringUtils.isEmpty(expression)&& !expression.contains(genericExpression)){
                expression+="||"+genericExpression;
            }
            pointcut.setExpression(expression);
            return new DefaultPointcutAdvisor(pointcut, dsAdvice());
        }


    }

    private DataSource buildDataSource(String url,String username,String password){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProps.getDriverClassName());
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(dataSourceProps.getInitialSize());
        dataSource.setMaxActive(dataSourceProps.getMaxActive());
        dataSource.setMinIdle(dataSourceProps.getMinIdle());
        dataSource.setDefaultAutoCommit(dataSourceProps.isDefaultAutoCommit());
        dataSource.setTestOnBorrow(dataSourceProps.isTestOnBorrow());
        dataSource.setValidationQuery(dataSourceProps.getValidationQuery());
        return  dataSource;
    }

    private void initDataSource(){
        masterDS=buildDataSource(dataSourceProps.getUrl(),dataSourceProps.getUsername(),dataSourceProps.getPassword());
        Map<String, DataSourceProps.MyDataSource> slaves = dataSourceProps.getSlaves();
        if (slaves != null && slaves.size() > 0){
            for (Map.Entry<String, DataSourceProps.MyDataSource> slave : slaves.entrySet()) {
                String key = slave.getKey();
                DataSourceProps.MyDataSource ds = slave.getValue();
                DataSource dataSource = buildDataSource(ds.getUrl(), ds.getUsername(), ds.getPassword());
                SlavesDS.put(key,dataSource);
            }
        } else {
            Integer slaveNums = dataSourceProps.getSlaveNums();
            if (slaveNums != null && slaveNums > 0){
                for (int i = 1; i <= slaveNums; i++) {
                    String key="slave_"+i;
                    DataSource slave=buildDataSource(dataSourceProps.getUrl(),dataSourceProps.getUsername(),dataSourceProps.getPassword());
                    SlavesDS.put(key,slave);
                }
            }
        }
    }

    @Bean
    public DataSource dataSourceProxy(){
        if (dataSourceProps.isMasterSlave()){
            DynamicDataSource proxy=new DynamicDataSource();
            if (masterDS==null){
                initDataSource();
            }
            Map<Object, Object> targetDataSource = new HashMap<Object, Object>();
            targetDataSource.put("master",masterDS);
            targetDataSource.putAll(SlavesDS);
            proxy.setTargetDataSources(targetDataSource);
            proxy.setDefaultTargetDataSource(masterDS);
            proxy.setDataSourceProps(dataSourceProps);
            return proxy;
        }else {
            return buildDataSource(dataSourceProps.getUrl(),dataSourceProps.getUsername(),dataSourceProps.getPassword());
        }
    }

    /**
     * SqlSessionFactory
     * @return
     * @throws Exception
    @Bean
    public SqlSessionFactory SqlSessionFactory()throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceProxy());
        factoryBean.setPlugins(new Interceptor[]{paginationInterceptor()
                ,optimisticLockerInterceptor()});
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(dataSourceProps.getMapperLocations()));
        return factoryBean.getObject();
    }*/

    /**
     * mybatisplus SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy());
        sqlSessionFactoryBean.setTypeAliasesPackage(dataSourceProps.getEntityPackages());
        List<Interceptor> interceptors=Lists.newArrayList();
        interceptors.add(new PaginationInterceptor()); //分页插件
        interceptors.add(new OptimisticLockerInterceptor()); //乐观锁插件
        if (dataSourceProps.isUsePerformanceInterceptor()){
            interceptors.add(new PerformanceInterceptor()); //性能分析插件 输出sql语句和执行时间
        }
        sqlSessionFactoryBean.setPlugins(interceptors.toArray(new Interceptor[]{}));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setGlobalConfig(new GlobalConfig()
                //.setSqlInjector(new LogicSqlInjector())   //sql注入器
                .setDbConfig(new GlobalConfig.DbConfig()
                        //.setTablePrefix("t_")  //表前缀
                        //.setLogicDeleteValue("-1")  //逻辑删除
                        //.setLogicNotDeleteValue("0")
                        //.setIdType(IdType.AUTO) //主键策略
                        .setCapitalMode(false) //大写下划线转换
                        .setFieldStrategy(FieldStrategy.NOT_EMPTY)
                ));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(dataSourceProps.getMapperLocations()));
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 事务管理
     * @return
     */
    @Bean
    public DataSourceTransactionManager TransactionManager(){
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager(dataSourceProxy());
        return transactionManager;
    }

    /**
     * 声明式事务
     * @return
     */
    @Bean
    public TransactionInterceptor txAdvice(){
        NameMatchTransactionAttributeSource source=new NameMatchTransactionAttributeSource();
        //只读事务
        RuleBasedTransactionAttribute readOnlyTx=new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        Map<String, TransactionAttribute> txMap = new HashMap<String, TransactionAttribute>();
        txMap.put("add*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("batch*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("search*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("count*", readOnlyTx);
        source.setNameMap( txMap );
        return new TransactionInterceptor(TransactionManager(),source);
    }


    /**
     * 事务aop配置
     * @return
     */
    @Bean
    public Advisor txAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(dataSourceProps.getTxAopExpression());
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }



}
