package com.zt.poseidon.common.amqp.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhouTian
 * @Description: 注册exchange和queue,且将它们绑定
 * @Date: 2018/7/25
 */
public class RabbitmqRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private RabbitmqProps rabbitmqProps;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, RabbitmqProps.ExchangeType> exchanges = rabbitmqProps.getExchanges();
        List<RabbitmqProps.MyQueue> queues =
                rabbitmqProps.getQueues();
        if (exchanges != null && exchanges.size() > 0){
            exchanges.forEach((name,exchangeType)->{
                //创建exchange
                if (!beanDefinitionRegistry.containsBeanDefinition(name)){
                    BeanDefinitionBuilder builder=BeanDefinitionBuilder
                        .genericBeanDefinition(exchangeType.getType())
                        .addConstructorArgValue(name);
                    beanDefinitionRegistry.registerBeanDefinition(name,builder.getBeanDefinition());
                }
            });
            if (queues != null && queues.size() > 0){
                queues.forEach((queue)->{
                    String queueName=queue.getQueueName();
                    String exchangeName = queue.getExchangeName();
                    String routingKey = queue.getRoutingKey();
                    Map<String, Object> arguments = queue.getArguments();
                    if (exchanges.containsKey(exchangeName)){
                        if (!beanDefinitionRegistry.containsBeanDefinition(queueName)){
                            //创建queue
                            BeanDefinitionBuilder queueBuilder=BeanDefinitionBuilder
                                    .genericBeanDefinition(Queue.class)
                                    .addConstructorArgValue(queueName)
                                    .addConstructorArgValue(true)
                                    .addConstructorArgValue(false)
                                    .addConstructorArgValue(false)
                                    .addConstructorArgValue(arguments);
                            beanDefinitionRegistry.registerBeanDefinition(queueName,queueBuilder.getBeanDefinition());

                            //将queue和exchange绑定
                            BeanDefinitionBuilder bindingBuilder=BeanDefinitionBuilder
                                    .genericBeanDefinition(Binding.class)
                                    .addConstructorArgValue(queueName)
                                    .addConstructorArgValue(Binding.DestinationType.QUEUE)
                                    .addConstructorArgValue(exchangeName)
                                    .addConstructorArgValue(routingKey)
                                    .addConstructorArgValue(new HashMap<String,Object>());
                            String bindingName="binding"+exchangeName+routingKey;
                            beanDefinitionRegistry.registerBeanDefinition(bindingName,bindingBuilder.getBeanDefinition());
                        }
                    }
                });
            }
        }

    }

    @Override
    public void setEnvironment(Environment environment) {
        if (this.rabbitmqProps ==null){
            rabbitmqProps= Binder.get(environment).bind(RabbitmqProps.class.getAnnotation(ConfigurationProperties.class).prefix()
                    , RabbitmqProps.class).get();
        }
    }

}
