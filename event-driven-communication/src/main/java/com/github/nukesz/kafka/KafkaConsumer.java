package com.github.nukesz.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka Consumer Service
 * Listens for messages from the Kafka topic and processes them.
 *
 * Kafka Consumer:
 * - A consumer subscribes to a topic and reads messages from it.
 * - Consumers in the same group share the workload of processing messages from topic partitions.
 */
@Service
class KafkaConsumer {

    /**
     * Listens for messages from "example-topic" and prints them to the console.
     *
     * KafkaListener:
     * - The @KafkaListener annotation marks this method as a Kafka message listener.
     * - It automatically subscribes to the specified topic and processes messages.
     *
     * Group ID:
     * - Consumers with the same group ID share messages (i.e., load balancing).
     * - Each partition is consumed by only one consumer per group.
     *
     * @param message The received message.
     */
    @KafkaListener(topics = "example-topic", groupId = "group_id")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
