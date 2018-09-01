package com.zt.poseidon.common.amqp.rabbitmq;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/11
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RabbitmqConfig.class,RabbitmqRegistrar.class})
@Documented
public @interface EnableRabbitmqManage {

}
