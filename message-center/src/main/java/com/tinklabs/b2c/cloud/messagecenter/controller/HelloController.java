package com.tinklabs.b2c.cloud.messagecenter.controller;

import com.tinklabs.b2c.cloud.messagecenter.sender.MessageSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class HelloController {
    @Autowired
    MessageSave messageSave;

    @RequestMapping(value={"/hello"}, method= RequestMethod.GET)
    @ResponseBody
    public String index(@RequestParam(value = "name",required = false,defaultValue = "you") String name) {
        return "Hello "+name+"，this is message push service!";
    }

    @RequestMapping(value={"/health"}, method= RequestMethod.GET)
    @ResponseBody
    public String alive(){
        return "Message push service is alive!";
    }

    @RequestMapping(value={"/version"}, method= RequestMethod.GET)
    @ResponseBody
    public String version(){
        return "0.0.0.1";
    }

    @RequestMapping(value={"/message"}, method= RequestMethod.GET)
    @ResponseBody
    public String sendMessage(@RequestParam(value = "message",required = false,defaultValue = "hello") String message) {
        try{
            messageSave.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "message : " + message + " -- stored in rabbitMQ done.";
    }

}
