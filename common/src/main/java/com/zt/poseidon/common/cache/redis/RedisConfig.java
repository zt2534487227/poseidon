package com.zt.poseidon.common.cache.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zt.poseidon.common.cache.ICacheService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: ZhouTian
 * @Description:  redis配置,用2套RedisTemplate,一个只写,一个只读
 * @Date: 2018/6/26
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedispProps.class)
public class RedisConfig  implements EnvironmentAware {

    private RedispProps redispProps;

    @Override
    public void setEnvironment(Environment environment) {
        if (this.redispProps ==null){
            redispProps= Binder.get(environment).bind(RedispProps.class.getAnnotation(ConfigurationProperties.class).prefix()
                    , RedispProps.class).get();
        }
    }

    /**
     * 配置 ConnectionFactory
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        //设置redis连接地址
        RedisStandaloneConfiguration standaloneConfiguration =new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(redispProps.getHost());
        standaloneConfiguration.setPort(redispProps.getPort());
        standaloneConfiguration.setPassword(RedisPassword.of(redispProps.getPassword()));
        return create(standaloneConfiguration);
    }

    private JedisConnectionFactory create(RedisStandaloneConfiguration standaloneConfiguration){
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder
                = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jedisPoolingClientConfigurationBuilder.poolConfig(jedisPoolConfig());
        JedisConnectionFactory factory=new JedisConnectionFactory(standaloneConfiguration,
                jedisPoolingClientConfigurationBuilder.build());
        return factory;
    }

    @Bean
    public CacheManager cacheManager(){
        return RedisCacheManager.create(jedisConnectionFactory());
    }


    /**
     * 配置JedisPoolConfig连接池
     */
     @Bean
     @ConfigurationProperties(prefix = "spring.redis.pool")
     public  JedisPoolConfig jedisPoolConfig(){
         JedisPoolConfig config=new JedisPoolConfig();
         return  config;
     }

    /**
     * 实例化 RedisTemplate
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        initDomainRedisTemplate(redisTemplate, jedisConnectionFactory());
        return redisTemplate;
    }


    /**
     * 将RedisTemplate对象注入到ICacheService中
     * @return
     */
    @Bean
    public ICacheService redisCacheService() {
        return  new RedisCacheService(redisTemplate());
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     * @param redisTemplate
     * @param redisConnectionFactory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory redisConnectionFactory) {
        //配置序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(jsonRedisSerializer());
        redisTemplate.setHashValueSerializer(jsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true); // 开启事务
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }

    private ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    private RedisSerializer jsonRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer(objectMapper());
    }


}
