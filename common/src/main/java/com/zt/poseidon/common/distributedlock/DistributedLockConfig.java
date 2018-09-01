package com.zt.poseidon.common.distributedlock;

import com.zt.poseidon.common.zookeeper.ZookeeperLock;
import com.zt.poseidon.common.zookeeper.ZookeeperProps;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/8
 */
@Configuration
@ConditionalOnProperty(name = "zt.zookeeper.lock",havingValue = "true")
public class DistributedLockConfig implements EnvironmentAware {

    private ZookeeperProps zookeeperProps;

    @Override
    public void setEnvironment(Environment environment) {
        if (this.zookeeperProps ==null){
            zookeeperProps= Binder.get(environment).bind(ZookeeperProps.class.getAnnotation(ConfigurationProperties.class).prefix()
                    , ZookeeperProps.class).get();
        }
    }

    @Bean
    public DistributedLock distributedLock(){
        return new ZookeeperLock(zookeeperProps);
    }


    @Bean
    public Advice lockAdvice(){
        return new DistributedLockAdvice(distributedLock());
    }

    @Bean
    public Advisor lockAdvisor(){
        String expression = zookeeperProps.getAopExpression();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return new DefaultPointcutAdvisor(pointcut, lockAdvice());
    }

}
