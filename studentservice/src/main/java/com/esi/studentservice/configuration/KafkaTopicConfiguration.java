package com.esi.studentservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// Task 1
@Configuration
public class KafkaTopicConfiguration {
    @Bean
    public NewTopic StudentTopicCreation(){
    return TopicBuilder.name("studentRequestCreatedTopic")
    .build();
    }

    @Bean
    public NewTopic AdvisorTopicCreation(){
    return TopicBuilder.name("advisorTopic")
    .build();
    }
}
// Task 1