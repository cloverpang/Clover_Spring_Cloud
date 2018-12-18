package com.tinklabs.b2c.cloud.splytwebhook.controller;

import com.tinklabs.b2c.cloud.splytwebhook.service.CacheComService;
import com.tinklabs.b2c.cloud.splytwebhook.service.MessageCeneterComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    CacheComService cacheComService;

    @Autowired
    MessageCeneterComService messageCeneterComService;


    @RequestMapping(value={"/hello"}, method= RequestMethod.GET)
    @ResponseBody
    public String index(@RequestParam(value = "name",required = false,defaultValue = "you") String name) {
        return "Hello " + name + "，this is splyt webhook!";
    }

    @RequestMapping(value={"/health"}, method= RequestMethod.GET)
    @ResponseBody
    public String alive(){
        return "splyt webhook is alive!";
    }

    @RequestMapping(value={"/test-cache"}, method= RequestMethod.GET)
    @ResponseBody
    public String testCache(@RequestParam(value = "name",required = false,defaultValue = "you") String name) {
        System.out.println("Received the message -- " + name);

        String s = "";
        String h = "";
        try{
            s = cacheComService.getVersion();
            h = cacheComService.cacheServiceSayHello(name);

        }catch (Exception e){
            e.printStackTrace();
        }

        return s + h ;
    }


    @RequestMapping("/call-cache")
    public String callCache() {
        ServiceInstance serviceInstance = loadBalancer.choose("b2c-cache-service");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }


    @RequestMapping(value={"/message-hello"}, method= RequestMethod.GET)
    @ResponseBody
    public String testMessage(@RequestParam(value = "name",required = false,defaultValue = "you") String name) {

        String s = "";
        try{
            s = messageCeneterComService.messageCenterSayHello(name);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "call message center - " + s;
    }

}
