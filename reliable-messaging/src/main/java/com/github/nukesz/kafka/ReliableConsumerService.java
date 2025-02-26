package com.github.nukesz.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

/**
 * Service responsible for consuming Kafka messages with retry and dead-letter topic (DLT) handling.
 */
@Service
public class ReliableConsumerService {

    /**
     * Listens to messages from the "reliable-topic" Kafka topic and applies automatic retries.
     *
     * Configures retry behavior with exponential backoff.
     * - attempts = "5": The consumer will retry up to 5 times.
     * - backoff: Defines the delay pattern for retries:
     *   - delay = 1000ms (1s) before first retry.
     *   - multiplier = 2: Each retry delay doubles (1s, 2s, 4s, etc.).
     *   - maxDelay = 5000ms (5s): Maximum delay before a retry attempt.
     * If the message still fails after all retries, it is moved to a Dead-Letter Topic (DLT).
     *
     * @param record The Kafka message to process.
     */
    @RetryableTopic(attempts = "5",
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 5000))
    @KafkaListener(topics = "reliable-topic", groupId = "reliable-group")
    public void processWithRetry(ConsumerRecord<String, String> record) {
        System.out.println("Received message: " + KafkaUtils.format(record));
        throw new RuntimeException("Something went wrong during message processing!");
    }

    /**
     * Handles messages that have failed all retry attempts and have been moved to the Dead-Letter Topic (DLT).
     *
     * @param message The failed Kafka message that was redirected to the DLT.
     */
    @DltHandler
    public void processDltTopicMessage(String message) {
        System.out.println("Dead-Letter Message received " + message);
    }
}
