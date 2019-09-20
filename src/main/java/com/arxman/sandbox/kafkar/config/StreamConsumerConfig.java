package com.arxman.sandbox.kafkar.config;

import com.arxman.sandbox.kafkar.model.MessageA;
import com.arxman.sandbox.kafkar.model.MessageB;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class StreamConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageA> messageAKafkaListenerContainerFactory() {
        ConsumerFactory<String, MessageA> consumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(MessageA.class)
        );

        ConcurrentKafkaListenerContainerFactory<String, MessageA> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageB> messageBKafkaListenerContainerFactory() {
        ConsumerFactory<String, MessageB> consumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(MessageB.class)
        );

        ConcurrentKafkaListenerContainerFactory<String, MessageB> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
