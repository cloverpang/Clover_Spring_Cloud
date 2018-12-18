package com.tinklabs.b2c.cloud.splytwebhook.controller;

import com.alibaba.fastjson.JSONObject;
import com.tinklabs.b2c.cloud.splytwebhook.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.splytwebhook.service.MessageCeneterComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

@RestController
@RequestMapping(value={"/spylt"})
public class SpyltController {
    @Autowired
    MessageCeneterComService messageCeneterComService;

    @RequestMapping(value={"/event"}, method= RequestMethod.POST)
    public ResponseEntity<ApiCallResult> getWebhook(HttpServletRequest request,
                                                    HttpServletResponse response){
        ApiCallResult result = new ApiCallResult();
        try{
            String receiveData = charReader(request);

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

            //send to rabbitMQ
            String eventMessage = "";
            eventMessage = receiveDataObject.toJSONString();
            System.out.println("eventMessage");
            System.out.println(receiveDataObject);

            boolean saveMessage = messageCeneterComService.saveMessage(eventMessage);
            if(saveMessage){
                result.setMessage("success");
            }else{
                result.setMessage("failed");
            }

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
