package com.github.nukesz.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StockPriceProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String[] STOCKS = {"AAPL", "GOOGL", "MSFT"};
    private static final Random RANDOM = new Random();

    public StockPriceProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends 10,000 stock price updates, assigning each stock to a partition.
     */
    public void sendStockPrices() {
        for (int i = 0; i < 10_000; i++) {
            String stock = STOCKS[RANDOM.nextInt(STOCKS.length)];
            String price = stock + ": $" + String.format("%.2f", (100 + RANDOM.nextDouble() * 1000));

            // The stock symbol is the key, ensuring Kafka assigns the same stock to the same partition
            kafkaTemplate.send(new ProducerRecord<>("stocks", stock, price));

            System.out.println("Sent: " + price);
        }
    }
}
