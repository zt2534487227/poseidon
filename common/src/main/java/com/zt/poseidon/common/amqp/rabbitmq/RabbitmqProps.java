package com.zt.poseidon.common.amqp.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/25
 */
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitmqProps {
    private String addresses;
    private String userName="guest";
    private String password="guest";
    private boolean publisherConfirms=true;
    private boolean publisherReturns=true;
    private String virtualHost="/";
    private Map<String,ExchangeType> exchanges;
    private List<MyQueue> queues;
    private boolean send;
    private boolean listen;


    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean isListen() {
        return listen;
    }

    public void setListen(boolean listen) {
        this.listen = listen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublisherConfirms() {
        return publisherConfirms;
    }

    public void setPublisherConfirms(boolean publisherConfirms) {
        this.publisherConfirms = publisherConfirms;
    }

    public boolean isPublisherReturns() {
        return publisherReturns;
    }

    public void setPublisherReturns(boolean publisherReturns) {
        this.publisherReturns = publisherReturns;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public Map<String, ExchangeType> getExchanges() {
        return exchanges;
    }

    public void setExchanges(Map<String, ExchangeType> exchanges) {
        this.exchanges = exchanges;
    }

    public List<MyQueue> getQueues() {
        return queues;
    }

    public void setQueues(List<MyQueue> queues) {
        this.queues = queues;
    }

    public static class MyQueue{
        private String queueName;
        private String exchangeName;
        private String routingKey;
        private Map<String,Object> arguments;

        public String getQueueName() {
            return queueName;
        }

        public void setQueueName(String queueName) {
            this.queueName = queueName;
        }

        public String getExchangeName() {
            return exchangeName;
        }

        public void setExchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public void setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
        }

        public Map<String, Object> getArguments() {
            return arguments;
        }

        public void setArguments(Map<String, Object> arguments) {
            this.arguments = arguments;
        }
    }


    public static enum ExchangeType {
        TopicExchange(org.springframework.amqp.core.TopicExchange.class),
        HeadersExchange(org.springframework.amqp.core.HeadersExchange.class),
        DirectExchange(org.springframework.amqp.core.DirectExchange.class),
        FanoutExchange(org.springframework.amqp.core.FanoutExchange.class);
        private Class type;

        ExchangeType(Class type) {
            this.type=type;
        }

        public Class getType() {
            return type;
        }
    }


}
