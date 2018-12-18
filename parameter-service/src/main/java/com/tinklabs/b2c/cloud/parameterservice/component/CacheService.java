package com.tinklabs.b2c.cloud.parameterservice.component;

import com.tinklabs.b2c.cloud.parameterservice.component.hystrix.CacheServiceHystrix;
import com.tinklabs.b2c.cloud.parameterservice.config.FeignConfigger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "b2c-cache-service",configuration = FeignConfigger.class,fallback = CacheServiceHystrix.class)
public interface CacheService {

    //@RequestMapping(value = "/hello")
    @GetMapping(value = "/hello")
    public String cacheServiceSayHello(@RequestParam(value = "name") String name);

    @GetMapping(value = "/cache/get")
    public String get(@RequestParam(value = "key") String key);

    @GetMapping(value = "/cache/post")
    public boolean save(@RequestParam(value = "key") String key, @RequestBody String value);

    @GetMapping(value = "/cache/remove")
    public boolean remove(@RequestParam(value = "key") String key);

}

