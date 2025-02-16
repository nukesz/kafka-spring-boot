package com.github.nukesz.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka configuration class responsible for defining topics.
 *
 * Kafka Topics:
 * - A topic is a logical channel to which producers send messages and from which consumers read messages.
 * - Messages are stored in partitions within a topic.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Creates a Kafka topic named "example-topic" with 3 partitions and 1 replica.
     *
     * Partitions:
     * - A topic can be divided into multiple partitions to allow parallel processing.
     * - Each partition is an ordered sequence of messages and can be processed independently.
     *
     * Replicas:
     * - A partition can have multiple replicas for fault tolerance.
     * - One replica is the leader that handles all reads/writes, and others act as backups.
     */
    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name("example-topic").partitions(3).replicas(1).build();
    }
}
