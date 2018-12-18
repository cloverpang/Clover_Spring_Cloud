package com.tinklabs.b2c.cloud.splytwebhook.service;

import com.tinklabs.b2c.cloud.splytwebhook.config.FeignConfigger;
import com.tinklabs.b2c.cloud.splytwebhook.service.hystrix.CacheComServiceHystrix;
import com.tinklabs.b2c.cloud.splytwebhook.service.hystrix.MessageCenterComServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "b2c-message-center",configuration = FeignConfigger.class,fallback = MessageCenterComServiceHystrix.class)
public interface MessageCeneterComService {

    //@RequestMapping(value = "/hello")
    @GetMapping(value = "/hello")
    public String messageCenterSayHello(@RequestParam(value = "name") String name);

    @GetMapping(value = "/message/saveMessage")
    public boolean saveMessage(@RequestBody String eventMessage);
}

