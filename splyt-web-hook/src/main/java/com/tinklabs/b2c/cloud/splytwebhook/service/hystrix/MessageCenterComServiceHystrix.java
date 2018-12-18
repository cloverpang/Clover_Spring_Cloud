package com.tinklabs.b2c.cloud.splytwebhook.service.hystrix;

import com.tinklabs.b2c.cloud.splytwebhook.service.CacheComService;
import com.tinklabs.b2c.cloud.splytwebhook.service.MessageCeneterComService;
import org.springframework.stereotype.Component;


@Component
public class MessageCenterComServiceHystrix implements MessageCeneterComService {

    public String messageCenterSayHello(String name) {
        return "message center failed!";
    }

    public boolean saveMessage(String eventMessage) {
        return false;
    }
}
