package com.zt.poseidon.common.cache;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/21
 */
public class CacheRegister implements ImportBeanDefinitionRegistrar{

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        String name = EnableCacheService.class.getName();
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(name, true));
        Class<?> register = attributes.getClass("register");
        BeanDefinitionBuilder beanBuilder=BeanDefinitionBuilder
                .genericBeanDefinition(register);
        beanDefinitionRegistry.registerBeanDefinition(register.getName(),beanBuilder.getBeanDefinition());
    }

}
