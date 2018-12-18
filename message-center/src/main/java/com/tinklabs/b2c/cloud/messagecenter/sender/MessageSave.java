package com.tinklabs.b2c.cloud.messagecenter.sender;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

//import org.springframework.amqp.rabbit.support.CorrelationData;

@Component
public class MessageSave implements RabbitTemplate.ConfirmCallback, ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功:" + correlationData);
        } else {
            System.out.println("消息发送失败:" + cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getMessageId()+ " 发送失败");

    }

    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String msg){

        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        System.out.println("开始发送消息 : " + msg.toLowerCase());

        Message message = new Message(msg.getBytes(),new MessageProperties());

        //Message message = new Message();
        //rabbitTemplate.send(msg);   //发消息，参数类型为org.springframework.amqp.core.Message
        rabbitTemplate.convertAndSend("topicExchange", "key.2",msg,correlationId); //转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
        //rabbitTemplate.convertSendAndReceive(msg); //转换并发送消息,且等待消息者返回响应消息。


        //String response = rabbitTemplate.convertSendAndReceive("topicExchange", "key.1", message, correlationId).toString();


        System.out.println("结束发送消息 : " + msg.toLowerCase());

        //String response = rabbitTemplate.convertSendAndReceive("topicExchange", "key.1", message, correlationId).toString();
        //System.out.println("消费者响应 : " + response + " 消息处理完成");
    }

}
