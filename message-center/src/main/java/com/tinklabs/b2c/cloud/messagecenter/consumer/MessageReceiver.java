package com.tinklabs.b2c.cloud.messagecenter.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.tinklabs.b2c.cloud.messagecenter.websocket.SplytEventWebSocket;

import java.io.IOException;
import org.springframework.amqp.core.Message;

@Component
public class MessageReceiver {
    @RabbitListener(queues = "hello.queue2")
    public void processMessage2(String message) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + message);

        for(SplytEventWebSocket webSocketServer :SplytEventWebSocket.webSocketSet){
            try {
                webSocketServer.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "hello.queue1")
    public void processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
    }
}
