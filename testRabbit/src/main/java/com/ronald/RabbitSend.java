package com.ronald;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class RabbitSend {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {

        RabbitSend rs = new RabbitSend();
        for (int i = 0; i < 10; i++){

            rs.send("第一条消息。" + i);
        }

        System.out.println(DateFormatUtils.format(new Date(), "HH:mm:ss") + ":发送完毕");
    }

    public void send(String msg){

        try {
            Channel channel = RabbitUtil.createChannel();
            //声明一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String msg = "hello world ...jiik.";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            RabbitUtil.close(channel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
