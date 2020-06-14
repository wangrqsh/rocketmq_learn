package com.example.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description TODO
 * @Author Administrator
 * @Email wangrqsh@163.com
 * @Date 2020/6/12 0012 上午 0:12
 * @Version 1.0
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception{
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("my_OnewayProducer");
        // 设置NameServer的地址
        producer.setNamesrvAddr("192.168.1.114:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10000; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("my_OnewayTopic" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);

        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
