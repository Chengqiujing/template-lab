package com.chengqj.study.redisdistributionlock.lock.impl;

import com.chengqj.study.redisdistributionlock.lock.BasicRedisLock;
import org.springframework.data.redis.core.RedisTemplate;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;


public class DefaultRedisLock implements BasicRedisLock {
    private static final String KEY = "redisLockKey123" ;

    private RedisTemplate<String,String> redisTemplate;

    public DefaultRedisLock(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @Override
    public boolean tryLock() {


        return false;
    }

    @Override
    public boolean lock(String value) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(KEY, value, 50, TimeUnit.SECONDS);
        return result;
    }

    @Override
    public boolean unlock() {

        return false;
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) {
        return false;
    }
}
