package org.example;

import org.example.services.JDAService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Bot {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Bot.class, args);
        context.getBean(JDAService.class).run();
    }
}