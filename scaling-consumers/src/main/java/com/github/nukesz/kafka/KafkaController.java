package com.github.nukesz.kafka;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final StockPriceProducer stockPriceProducer;

    public KafkaController(StockPriceProducer stockPriceProducer) {
        this.stockPriceProducer = stockPriceProducer;
    }

    @PostMapping("/send")
    public void sendStockPrices() {
        stockPriceProducer.sendStockPrices();
    }
}
