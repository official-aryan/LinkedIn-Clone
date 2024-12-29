package com.example.post_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic postCreatedTopic()
    {
        return new NewTopic("post-Created",3,(short) 1);
    }

}
