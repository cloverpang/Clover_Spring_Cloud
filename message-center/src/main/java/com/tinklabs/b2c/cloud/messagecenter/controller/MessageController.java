package com.tinklabs.b2c.cloud.messagecenter.controller;

import com.tinklabs.b2c.cloud.messagecenter.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.messagecenter.sender.MessageSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@RequestMapping(value={"/message"})
public class MessageController {

    @Autowired
    MessageSave messageSave;

    @RequestMapping(value={"/saveMessage"}, method= RequestMethod.POST)
    public boolean saveMessage(@RequestBody String eventMessage){
        try{
            //send to rabbitMQ
            System.out.println("eventMessage:" + eventMessage);
            messageSave.send(eventMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
