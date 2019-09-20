package com.arxman.sandbox.kafkar.service;

import com.arxman.sandbox.kafkar.model.MessageA;
import com.arxman.sandbox.kafkar.model.MessageB;
import com.arxman.sandbox.kafkar.stream.consumer.StreamConsumer;
import com.arxman.sandbox.kafkar.stream.producer.StreamProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamService {

    private final Logger logger = LoggerFactory.getLogger(StreamService.class);

    @Autowired
    private StreamProducer streamProducer;

    @Autowired
    private StreamConsumer streamConsumer;

    public void pauseListener() {
        streamConsumer.pauseListener();
    }

    public void resumeListener() {
        streamConsumer.resumeListener();
    }

    public void stopListener() {
        streamConsumer.stopListener();
    }

    public void startListener() {
        streamConsumer.startListener();
    }

    public void sendMessage(String message) {
        streamProducer.produce(message);
    }

    public void sendMessage(int partition, String message) {
        streamProducer.produce(partition, "fixKey", message);
    }

    public void sendMessageA(String info, int number) {
        MessageA messageA = new MessageA(info, number);
        streamProducer.produceMessageA(messageA);
    }

    public void sendMessageB(String info, int number) {
        MessageB messageB = new MessageB(info, number);
        streamProducer.produceMessageB(messageB);
    }

}
