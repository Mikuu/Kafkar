package com.arxman.sandbox.kafkar.stream.producer;

import com.arxman.sandbox.kafkar.config.StreamProducerConfig;
import com.arxman.sandbox.kafkar.model.MessageA;
import com.arxman.sandbox.kafkar.model.MessageB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class StreamProducer {

    private final Logger logger = LoggerFactory.getLogger(StreamProducer.class);

    @Value("${kafka.topic.topicA}")
    private String topicA;

    @Value("${kafka.topic.topicB}")
    private String topicB;

    @Value("${kafka.topic.topicS}")
    private String topicS;

    @Autowired
    private StreamProducerConfig producerConfig;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String message) {
        kafkaTemplate.send(topicS, message);
        logger.info("FBI --> Sent string message='{}' to topic='{}'", message, topicS);
    }

    public void produce(int partition, String key, String message) {
        kafkaTemplate.send(topicS, partition, key, message);
        logger.info("FBI --> Sent string message='{}' to topic='{}' with partition='{}', key='{}'", message, topicS, partition, key);
    }

    public void produceMessageA(MessageA message) {
        KafkaTemplate<String, MessageA> kafkaTemplate = producerConfig.MessageAKafkaTemplate();
        Message<MessageA> data = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topicA)
                .build();

        kafkaTemplate.send(data);
        logger.info("FBI --> Sent MessageA='{}' to topic='{}'", message.toString(), topicA);
    }

    public void produceMessageB(MessageB message) {
        KafkaTemplate<String, MessageB> kafkaTemplate = producerConfig.MessageBKafkaTemplate();
        Message<MessageB> data = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topicB)
                .build();

        kafkaTemplate.send(data);
        logger.info("FBI --> Sent MessageB='{}' to topic='{}'", message.toString(), topicB);
    }



}
