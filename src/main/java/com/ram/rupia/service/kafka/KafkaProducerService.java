package com.ram.rupia.service.kafka;


import com.rupia.kafa.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


/**
 * Created by Ram Mandal on 14/12/2025
 *
 * @System: Apple M1 Pro
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, TransactionEvent> eventKafkaTemplate;

    public void publishEvent(String topic, TransactionEvent event) {
        CompletableFuture<SendResult<String, TransactionEvent>> future = eventKafkaTemplate.send(topic, event);
        future.handleAsync((result, ex) -> {
            if (result != null) {
                System.out.println("Event sent successfully: " + event);
            } else if (ex != null) {
                System.err.println("Failed to send event: " + ex.getMessage());
            }
            return null;
        });
    }
}

