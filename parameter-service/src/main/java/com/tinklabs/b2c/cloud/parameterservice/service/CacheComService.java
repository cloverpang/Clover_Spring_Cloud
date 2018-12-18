package com.tinklabs.b2c.cloud.parameterservice.service;

import com.tinklabs.b2c.cloud.parameterservice.config.FeignConfigger;
import com.tinklabs.b2c.cloud.parameterservice.service.hystrix.CacheComServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "b2c-cache-service",configuration = FeignConfigger.class,fallback = CacheComServiceHystrix.class)
public interface CacheComService {

    //@RequestMapping(value = "/hello")
    @GetMapping(value = "/hello")
    public String cacheServiceSayHello(@RequestParam(value = "name") String name);

    //@RequestMapping(value = "/health")
    @GetMapping(value = "/health")
    public String cacheServiceHealthCheck();

    //@RequestMapping(value = "/version")
    @GetMapping(value = "/version")
    public String getVersion();
}

