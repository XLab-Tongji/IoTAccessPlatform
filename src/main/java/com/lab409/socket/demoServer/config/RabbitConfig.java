package com.lab409.socket.demoServer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue order() {
        return new Queue("order");
    }

    @Bean
    public Queue clientMsg() {
        return new Queue("clientMsg");
    }

}
