package com.ronald;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitReceive {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        RabbitReceive rr = new RabbitReceive();
        rr.receive();
    }

    public void receive(){

        try {
            Channel channel = RabbitUtil.createChannel();
            //声明一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body, "UTF-8");
                    System.out.println("有一条新消息：" + msg);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
