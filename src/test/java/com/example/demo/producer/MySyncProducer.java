package com.example.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description TODO
 * @Author Administrator
 * @Email wangrqsh@163.com
 * @Date 2020/6/11 0011 下午 23:54
 * @Version 1.0
 */
public class MySyncProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("my_SyncProducer");
        // 设置NameServer的地址
        producer.setNamesrvAddr("192.168.1.114:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10000; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("my_TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        Thread.sleep(500000);
        producer.shutdown();
    }
}
