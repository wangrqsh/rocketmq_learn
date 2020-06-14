package com.example.demo.cfg;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author Administrator
 * @Email wangrqsh@163.com
 * @Date 2020/6/8 0008 下午 23:50
 * @Version 1.0
 */
@Configuration
public class MQProducerConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerConfiguration.class);

    @Value("${rocketmq.producer.groupName}")
    private String             groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String             namesrvAddr;
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer            maxMessageSize;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer            sendMsgTimeout;
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer            retryTimesWhenSendFailed;

    @Bean
    @ConditionalOnMissingBean
    public DefaultMQProducer defaultMQProducer() throws RuntimeException {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        try {
            producer.start();
            LOGGER.info("producer is started. groupName:{}, namesrvAddr: {}", groupName, namesrvAddr);
        } catch (MQClientException e) {
            LOGGER.error("failed to start producer.", e);
            throw new RuntimeException(e);
        }
        return producer;
    }


}
