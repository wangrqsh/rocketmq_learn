package com.example.demo.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author Administrator
 * @Email wangrqsh@163.com
 * @Date 2020/6/9 0009 上午 0:14
 * @Version 1.0
 */

@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private static String TOPIC = "DemoTopic";
    private static String TAGS = "glmapperTags";

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping("send")
    public String test() throws Throwable {
        Message msg = new Message(TOPIC, TAGS, ("Say Hello RocketMQ to Glmapper").getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 调用客户端发送消息
        SendResult sendResult = defaultMQProducer.send(msg);
        LOGGER.info("sendResult: {}.",sendResult);
        return "SUCCESS";
    }
}
