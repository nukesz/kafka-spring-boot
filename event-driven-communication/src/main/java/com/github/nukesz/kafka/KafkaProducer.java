package com.github.nukesz.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka Producer Service
 * Responsible for sending messages to the Kafka topic.
 *
 * Kafka Producer:
 * - A producer sends messages to Kafka topics.
 * - It does not send directly to a consumer but to a topic, which Kafka brokers store and distribute.
 */
@Service
class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a message to the Kafka topic "example-topic".
     *
     * KafkaTemplate:
     * - KafkaTemplate is a high-level abstraction that simplifies producing messages in Spring Boot.
     * - It handles serialization and communication with Kafka brokers.
     *
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        kafkaTemplate.send("example-topic", message);
    }
}
