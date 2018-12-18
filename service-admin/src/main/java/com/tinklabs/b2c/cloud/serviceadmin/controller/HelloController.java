package com.tinklabs.b2c.cloud.serviceadmin.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@RestController
@SpringBootApplication
public class HelloController {

    @RequestMapping(value={"/hello"}, method= RequestMethod.GET)
    @ResponseBody
    public String index(@RequestParam(value = "name",required = false,defaultValue = "you") String name) {
        return "Hello "+name+"，this is Spring boot admin!";
    }

    @RequestMapping(value={"/health"}, method= RequestMethod.GET)
    @ResponseBody
    public String alive(){
        return "Spring boot admin! is alive!";
    }

    @RequestMapping(value={"/version"}, method= RequestMethod.GET)
    @ResponseBody
    public String version(){
        return "0.0.0.1";
    }

}
