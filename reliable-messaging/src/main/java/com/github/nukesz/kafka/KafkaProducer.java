package com.github.nukesz.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Sending messages to Kafka with proper acknowledgment
    public void sendMessage(String message) {
        String topic = "reliable-topic";
        kafkaTemplate.send(topic, message)
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        // Log the failure; Spring Retry will trigger a retry if an exception is thrown.
                        System.err.println("Failed to send message: " + throwable.getMessage());
                        throw new RuntimeException("Send failed", throwable);
                    } else {
                        System.out.println("Message sent successfully to topic: " + topic +
                                " | Partition: " + result.getRecordMetadata().partition() +
                                " | Offset: " + result.getRecordMetadata().offset());
                    }
                });
    }
}
