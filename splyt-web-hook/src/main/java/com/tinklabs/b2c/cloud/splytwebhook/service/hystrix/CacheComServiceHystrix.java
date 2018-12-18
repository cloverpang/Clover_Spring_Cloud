package com.tinklabs.b2c.cloud.splytwebhook.service.hystrix;

import com.tinklabs.b2c.cloud.splytwebhook.service.CacheComService;
import org.springframework.stereotype.Component;


@Component
public class CacheComServiceHystrix implements CacheComService {
    public String cacheServiceSayHello(String name) {
        return "hello - " +name+", this message send failed ";
    }

    public String cacheServiceHealthCheck() {
        return "cache service is dead! ";
    }

    @Override
    public String getVersion() {
        return "cache service is not alive! ";
    }
}
