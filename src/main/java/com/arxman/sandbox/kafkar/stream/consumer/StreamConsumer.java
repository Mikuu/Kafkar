package com.arxman.sandbox.kafkar.stream.consumer;

import com.arxman.sandbox.kafkar.model.MessageA;
import com.arxman.sandbox.kafkar.model.MessageB;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class StreamConsumer {

    private final Logger logger = LoggerFactory.getLogger(StreamConsumer.class);

    private final String listenerIdS = "listenerS";
    private final String listenerIdA = "listenerA";
    private final String listenerIdB = "listenerB";

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    public void startListener() {
        registry.getListenerContainer(listenerIdS).start();
        logger.info("FBI --> listener {} started", listenerIdS);
    }

    public void stopListener() {
        registry.getListenerContainer(listenerIdS).stop();
        logger.info("FBI --> listener {} stopped", listenerIdS);
    }

    public void pauseListener() {
        registry.getListenerContainer(listenerIdS).pause();
        logger.info("FBI --> listener {} paused", listenerIdS);
    }

    public void resumeListener() {
        registry.getListenerContainer(listenerIdS).resume();
        logger.info("FBI --> listener {} resumed", listenerIdS);
    }

    @KafkaListener(id = listenerIdS, idIsGroup = false, groupId = "${kafka.group.groupS}", topics = "${kafka.topic.topicS}")
    public void consume(ConsumerRecord<?, ?> consumerRecord) {
        String message = consumerRecord.value().toString();
        logger.info("FBI --> Received string message '{}'", message);
    }

    @KafkaListener(id = listenerIdA, idIsGroup = false, groupId = "${kafka.group.groupA}", topics = "${kafka.topic.topicA}",
            containerFactory = "messageAKafkaListenerContainerFactory")
    public void consumeMessageA(@Payload MessageA data,
                                @Headers MessageHeaders headers) {
        logger.info("FBI --> Received messageA '{}' with headers:", data);

        headers.keySet().forEach(key -> {
            logger.info("{}: {}", key, headers.get(key));
        });
    }

    @KafkaListener(id = listenerIdB, idIsGroup = false, groupId = "${kafka.group.groupB}", topics = "${kafka.topic.topicB}",
            containerFactory = "messageBKafkaListenerContainerFactory")
    public void consumeMessageB(@Payload MessageB data,
                                @Headers MessageHeaders headers) {
        logger.info("FBI --> Received messageB '{}' with headers:", data);

        headers.keySet().forEach(key -> {
            logger.info("{}: {}", key, headers.get(key));
        });
    }


}
