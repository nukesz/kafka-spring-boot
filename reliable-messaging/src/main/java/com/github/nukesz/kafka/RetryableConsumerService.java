package com.github.nukesz.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RetryableConsumerService {

    private static final int MAX_RETRIES = 3;
    private final AtomicInteger retryCount = new AtomicInteger(0);

    @KafkaListener(topics = "reliable-topic", groupId = "reliable-group")
    public void consumeWithRetry(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            System.out.println("Received message (attempt " + retryCount.get() + "): " + record.value());

            if (retryCount.getAndIncrement() <= MAX_RETRIES) {
                throw new RuntimeException("Simulated failure for retry testing");
            }

            acknowledgment.acknowledge();
            System.out.println("Message processed successfully after " + retryCount.get() + " attempts: " + record.value());
            retryCount.set(0);

        } catch (Exception e) {
            System.err.println("Retry attempt " + retryCount.get() + " failed for message: " + record.value());
        }
    }
}
