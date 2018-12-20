package com.tinklabs.b2c.cloud.cacheservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class SimpleRedisUtil {
//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public synchronized void set( String key,  String value, int expiry){
        try {
            System.out.println("the key is " + key);
            System.out.println("the value is " + value);
//            HashOperations hashOperations = redisTemplate.opsForHash();
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key,value,expiry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized String get(String key){
        try {
            if (!exists(key.trim())) {
                return null;
            }
            ValueOperations valueOperations = redisTemplate.opsForValue();
            return (String) valueOperations.get(key.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized Long del(String key){
        try{
           boolean del = redisTemplate.delete(key.trim());
           if(del){
               return 1L;
           }else{
               return 0L;
           }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized boolean exists(String key){
        try {
            System.out.println("the key is " + key);
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
