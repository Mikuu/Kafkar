package com.arxman.sandbox.kafkar.stream.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StreamProducer {

    private final Logger logger = LoggerFactory.getLogger(StreamProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String topic, String message) {
        kafkaTemplate.send(topic, message);
        logger.info("FBI --> Sent message='{}' to topic='{}'", message, topic);
    }

    public void produce(String topic, int partition, String key, String message) {
        kafkaTemplate.send(topic, partition, key, message);
        logger.info("FBI --> Sent message='{}' to topic='{}' with partition='{}', key='{}'", message, topic, partition, key);
    }
}
