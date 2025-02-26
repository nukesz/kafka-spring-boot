package com.github.nukesz.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockPriceConsumer {

    /**
     * Listens for stock price updates.
     * Multiple consumers in the same group will balance the load dynamically.
     */
    @KafkaListener(topics = "stocks", groupId = "stock-group")
    public void consume(String message) {
        System.out.println("Processed: " + message + " by " + Thread.currentThread().getName());
    }
}
