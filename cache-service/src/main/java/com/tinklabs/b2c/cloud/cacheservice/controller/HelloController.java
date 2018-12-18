package com.tinklabs.b2c.cloud.cacheservice.controller;

import com.tinklabs.b2c.cloud.cacheservice.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class HelloController {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private String redisPort;

    @RequestMapping(value={"/hello"}, method= RequestMethod.GET)
    @ResponseBody
    public String index(@RequestParam(value = "name",required = false,defaultValue = "you") String name) {
        System.out.println("clover 111");
        return "Hello "+name+"，this is cache service!" + redisHost + ":" + redisPort;
    }

    @RequestMapping(value={"/health"}, method= RequestMethod.GET)
    @ResponseBody
    public String alive(){
        return "Cache Service is alive!";
    }

    @RequestMapping(value={"/version"}, method= RequestMethod.GET)
    @ResponseBody
    public String version(){
        return "0.0.0.1";
    }


    //3 simple functions for consumer
    @RequestMapping(value={"/getCache"}, method= RequestMethod.GET)
    @ResponseBody
    public String getCache(@RequestParam(value = "key",required = false,defaultValue = "") String key){
        String r = RedisUtil.get(key);
        return r;
    }

    @RequestMapping(value={"/postCache"}, method= RequestMethod.POST)
    @ResponseBody
    public boolean postCache(@RequestParam(value = "value",required = true) String value,
                             @RequestParam(value = "key",required = true) String key){
        try {
            RedisUtil.set(key, value, 36000);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value={"/removeCache"}, method= RequestMethod.POST)
    @ResponseBody
    public boolean removeCache(@RequestParam(value = "key",required = true) String key){
        try {
            long val = RedisUtil.del(key);
            if(val>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

}
