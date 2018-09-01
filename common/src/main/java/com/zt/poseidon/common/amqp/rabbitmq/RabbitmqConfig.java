package com.zt.poseidon.common.amqp.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/25
 */
@Configuration
public class RabbitmqConfig implements EnvironmentAware {

    private static final Logger log=LoggerFactory.getLogger(RabbitmqConfig.class);

    private RabbitmqProps rabbitmqProps;

    @Override
    public void setEnvironment(Environment environment) {
        if (this.rabbitmqProps ==null){
            rabbitmqProps= Binder.get(environment).bind(RabbitmqProps.class.getAnnotation(ConfigurationProperties.class).prefix()
                    , RabbitmqProps.class).get();
        }
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitmqProps.getAddresses());
        connectionFactory.setUsername(rabbitmqProps.getUserName());
        connectionFactory.setPassword(rabbitmqProps.getPassword());
        connectionFactory.setVirtualHost(rabbitmqProps.getVirtualHost());
        connectionFactory.setPublisherConfirms(rabbitmqProps.isPublisherConfirms()); //消息回调，必须要设置
        connectionFactory.setPublisherReturns(rabbitmqProps.isPublisherReturns());
        return connectionFactory;
    }

    /**
     * 将 RabbitTemplate 注入到 RabbitmqSend 中
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "spring.rabbitmq.send",havingValue = "true")
    public RabbitmqSend rabbitmqSend(){
        return new RabbitmqSend(rabbitTemplate());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConditionalOnProperty(name = "spring.rabbitmq.send",havingValue = "true")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setEncoding("UTF-8");
        return template;
    }

    //使用@RabbitListener必须配置该选项
    @Bean
    @ConditionalOnProperty(name = "spring.rabbitmq.listen",havingValue = "true")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        /*factory.setConcurrentConsumers(rabbitmqProps.getConcurrentConsumers());
        factory.setMaxConcurrentConsumers(rabbitmqProps.getMaxConcurrentConsumers());  //接收的最大消息数量*/
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);// 确认模式：手动
        return factory;
    }


}
