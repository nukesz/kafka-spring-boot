package com.github.nukesz.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class StocksTopicConfig {

    @Bean
    public NewTopic stocksTopic() {
        return TopicBuilder.name("stocks").partitions(3).replicas(1).build();
    }
}
