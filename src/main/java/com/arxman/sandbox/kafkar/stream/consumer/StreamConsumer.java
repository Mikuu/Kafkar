package com.arxman.sandbox.kafkar.stream.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StreamConsumer {

    private final Logger logger = LoggerFactory.getLogger(StreamConsumer.class);

    @KafkaListener(topics = "${kafka.topic.topicA}")
    public void consume(ConsumerRecord<?, ?> consumerRecord) {
        String message = consumerRecord.value().toString();
        logger.info("FBI --> Received message '{}'", message);
    }
}
