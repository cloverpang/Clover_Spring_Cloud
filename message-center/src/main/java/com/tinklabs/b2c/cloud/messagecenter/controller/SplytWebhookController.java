package com.tinklabs.b2c.cloud.messagecenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.tinklabs.b2c.cloud.messagecenter.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.messagecenter.sender.MessageSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

@RestController
@SpringBootApplication
@RequestMapping(value={"/splyt"})
public class SplytWebhookController {

    @Autowired
    MessageSave messageSave;

    @RequestMapping(value={"/event"}, method= RequestMethod.POST)
    public ResponseEntity<ApiCallResult> getWebhook(HttpServletRequest request,
                                                    HttpServletResponse response){
        ApiCallResult result = new ApiCallResult();
        try{
            String receiveData = charReader(request);

            //JsonUtils.toJson(receiveData);
            JSONObject receiveDataObject = JSONObject.parseObject(receiveData);

            JSONObject event = receiveDataObject.getJSONObject("event");
            if(null != event){
                String event_name = event.getString("name");
                String event_time = event.getString("time");
                System.out.println("Get event -- " + "name:" + event_name + ",time:" + event_time);
            }

            JSONObject data = receiveDataObject.getJSONObject("data");
            if(null != data){
                String booking_id = event.getString("booking_id");
                String status = event.getString("status");
                System.out.println("Get data -- " + "booking_id:" + booking_id + ",status:" + status);
            }

            result.setMessage("success");


            //send to rabbitMQ
            String eventMessage = "";
            eventMessage = receiveDataObject.toJSONString();
            System.out.println("eventMessage");
            System.out.println(receiveDataObject);
            messageSave.send(eventMessage);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String charReader(HttpServletRequest request) {
        String str, wholeStr = "";
        try
        {
            BufferedReader br = request.getReader();
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
        }catch (Exception e){

        }

        //System.out.println(wholeStr);

        return wholeStr;
    }
}
