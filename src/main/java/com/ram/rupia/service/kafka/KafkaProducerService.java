package com.ram.rupia.service.kafka;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Ram Mandal on 14/12/2025
 *
 * @System: Apple M1 Pro
 */
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String topic,String message) {
        this.kafkaTemplate.send(topic, message);
    }
}
