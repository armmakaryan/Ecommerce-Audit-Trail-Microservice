package com.smartcode.audit_trail.configuration;

import com.smartCode.audit_traill.model.dto.CreateActionDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.activity.groupId}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> prop = new HashMap<>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        prop.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
        prop.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
        prop.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        prop.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,StringDeserializer.class);
        prop.put(JsonDeserializer.KEY_DEFAULT_TYPE, "java.lang.String");
        prop.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,JsonDeserializer.class);
        prop.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.smartcode.audit_trail.model.dto.CreateActionDto");
        prop.put(JsonDeserializer.TRUSTED_PACKAGES, "com.smartcode");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return prop;
    }

    @Bean
    public ConsumerFactory<String, CreateActionDto> notificationConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//                new StringDeserializer(),
//                new JsonDeserializer<>(CreateActionDto.class));
    }

    @Bean
    @Primary
    public KafkaListenerEndpointRegistry endpointRegistry() {
        return new KafkaListenerEndpointRegistry();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateActionDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreateActionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }
}