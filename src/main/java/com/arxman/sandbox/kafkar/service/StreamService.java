package com.arxman.sandbox.kafkar.service;

import com.arxman.sandbox.kafkar.stream.consumer.StreamConsumer;
import com.arxman.sandbox.kafkar.stream.producer.StreamProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StreamService {

    private final Logger logger = LoggerFactory.getLogger(StreamService.class);

    @Autowired
    private StreamProducer streamProducer;

    @Autowired
    private StreamConsumer streamConsumer;

    @Value("${kafka.topic.topicA}")
    private String topicA;

    public void sendMessage(String message) {
        streamProducer.produce(topicA, message);
    }

    public void sendMessage(int partition, String message) {
        streamProducer.produce(topicA, partition, "fixKey", message);
    }

}
