package com.tinklabs.b2c.cloud.parameterservice.component;

import com.tinklabs.b2c.cloud.parameterservice.util.JsonUtils;
import com.tinklabs.b2c.cloud.parameterservice.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("cacheComponent")
public class CacheComponent<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheComponent.class);

    @Autowired
//    @Qualifier("cacheService")
    private CacheService cacheService;

    public void set(String key, T value) {
        try{
            cacheService.save(key, JsonUtils.toJson(value));
            System.out.println("save cache success! " + JsonUtils.toJson(value));
        }catch (Exception e){
            LOGGER.error("set the cache into cache exception : " + e.getMessage());
        }
    }

    public T get(String key, Class<T> clazz) {
        try{
            String value = cacheService.get(key);
            if(StringUtils.isNotEmpty(value)){
                System.out.println("cache key is : " + key);
                System.out.println("get cache success! " + value);
                return JsonUtils.toBean(value,clazz);
            }else{
                return null;
            }

        }catch (Exception e){
            LOGGER.error("get the cache exception : " + e.getMessage());
            return null;
        }
    }

    public boolean remove(String key) {
        try{
            boolean val = cacheService.remove(key);
            return val;
        }catch (Exception e){
            LOGGER.error("remove the cache exception : " + e.getMessage());
            return false;
        }
    }
}
