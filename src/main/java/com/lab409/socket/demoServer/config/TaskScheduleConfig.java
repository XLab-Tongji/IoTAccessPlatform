package com.lab409.socket.demoServer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.lab409.socket.demoServer.service")
@EnableScheduling
public class TaskScheduleConfig {

}
