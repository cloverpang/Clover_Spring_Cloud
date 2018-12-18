package com.tinklabs.b2c.cloud.parameterservice.component.hystrix;

import com.tinklabs.b2c.cloud.parameterservice.component.CacheService;
import org.springframework.stereotype.Component;


@Component
public class CacheServiceHystrix implements CacheService {
    public String cacheServiceSayHello(String name) {
        return "hello - " +name+", say hello to cache service failed.";
    }

    public String get(String key) {
        return "get cache failed.";
    }

    public boolean save(String key, String value) {
        return false;
    }

    public boolean remove(String key) {
        return false;
    }
}
