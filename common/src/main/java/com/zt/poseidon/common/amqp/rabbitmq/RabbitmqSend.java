package com.zt.poseidon.common.amqp.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Author: ZhouTian
 * @Description: 消息发送
 * @Date: 2018/7/25
 */
public class RabbitmqSend {
    private static final Logger log=LoggerFactory.getLogger(RabbitmqSend.class);

    private RabbitTemplate amqpTemplate;

    public RabbitmqSend(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(String exchange,String routingKey,String message){
        amqpTemplate.setReturnCallback((m, replyCode, replyText, ex, key) -> {
            //消息找不到路由队列
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", m, replyCode, replyText, ex, key);
        });
        amqpTemplate.setConfirmCallback(((correlationData, ack, cause) ->{
            if (ack) {
                log.info("消息发送到exchange成功");
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
        } ));
        amqpTemplate.convertAndSend(exchange,routingKey,message);
    }

    public void sendMessage(String exchange, String routingKey, String message
                            , RabbitTemplate.ReturnCallback returnCallback
                            , RabbitTemplate.ConfirmCallback confirmCallback){
        amqpTemplate.setReturnCallback(returnCallback);
        amqpTemplate.setConfirmCallback(confirmCallback);
        amqpTemplate.convertAndSend(exchange,routingKey,message);
    }


}
