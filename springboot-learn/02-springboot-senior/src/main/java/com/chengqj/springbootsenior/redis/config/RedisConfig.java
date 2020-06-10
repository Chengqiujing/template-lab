package com.chengqj.springbootsenior.redis.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 *  JedisPoolConfig配置连接池
 */
@Configuration
public class RedisConfig {
    /**
     * 1.创建连接池配置
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "aaa.bbb")//会将前缀相同的内容创建一个实体 这里前缀没事，后缀一定要和方法名相同，参照例子
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //这里注释掉是因为配置了@ConfigurationProperties(prefix = "aaa.bbb")
//        jedisPoolConfig.setMaxIdle(5);
//        jedisPoolConfig.setMinIdle(1);
//        jedisPoolConfig.setMaxTotal(10);
        //在这里取不出来 因为spring还没有进行配置 它是在返回后注入的
        System.out.println(jedisPoolConfig.getMaxIdle());
        System.out.println(jedisPoolConfig.getMaxTotal());
        System.out.println(jedisPoolConfig.getMinIdle());
        return jedisPoolConfig;
    }

    /**
     * 2.基于连接池，创建JedisConnectionFactory 配置redis链接信息
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory factory(JedisPoolConfig config){
        //验证之前的JedisPoolConfig配置
        System.out.println(config.getMaxIdle());
        System.out.println(config.getMaxTotal());
        System.out.println(config.getMinIdle());
        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName("127.0.0.1");
//        factory.setPort(6379);
        factory.setPoolConfig(config);
        return factory;
    }
    /**
     * 3.创建RedisTemplate：执行redis操作
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
