package com.tinklabs.b2c.cloud.cacheservice.controller;

import com.tinklabs.b2c.cloud.cacheservice.utils.RedisUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class CacheController {
    @RequestMapping(value={"/cache/get"}, method= RequestMethod.GET)
    @ResponseBody
    public String getCache(@RequestParam(value = "key",required = true,defaultValue = "") String key){
        String r = RedisUtil.get(key);
        return r;
    }

    @RequestMapping(value={"/cache/post"}, method= RequestMethod.POST)
    @ResponseBody
    public boolean postCache(@RequestParam(value = "key",required = true) String key, @RequestBody String value){
        try {
            RedisUtil.set(key, value, 216000);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value={"/cache/remove"}, method= RequestMethod.POST)
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
