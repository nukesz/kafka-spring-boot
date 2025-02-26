package com.github.nukesz.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScalingConsumersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScalingConsumersApplication.class, args);
    }
}
