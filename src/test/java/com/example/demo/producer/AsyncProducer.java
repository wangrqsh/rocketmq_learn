package com.example.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author Administrator
 * @Email wangrqsh@163.com
 * @Date 2020/6/12 0012 上午 0:01
 * @Version 1.0
 */
public class AsyncProducer {
        public static void main(String[] args) throws Exception {
            // 实例化消息生产者Producer
            DefaultMQProducer producer = new DefaultMQProducer("my_AsyncProducer");
            // 设置NameServer的地址
            producer.setNamesrvAddr("192.168.1.114:9876");
            // 启动Producer实例
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(0);

            int messageCount = 1000;
            // 根据消息数量实例化倒计时计算器
            final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
            for (int i = 0; i < messageCount; i++) {
                final int index = i;
                // 创建消息，并指定Topic，Tag和消息体
                Message msg = new Message("my_asyncTopic",
                        "TagA",
                        "AUTO_CREATE_TOPIC_KEY",
                        "Hello world2".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // SendCallback接收异步返回结果的回调
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index,
                                sendResult.getMsgId());
                    }
                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            }
            // 等待5s
            countDownLatch.await(5, TimeUnit.SECONDS);
            // 如果不再发送消息，关闭Producer实例。
            producer.shutdown();
        }
    }

