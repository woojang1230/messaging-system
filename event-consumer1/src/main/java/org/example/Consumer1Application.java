package org.example;

import java.util.function.Consumer;

import org.example.dto.Product;
import org.example.event.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Consumer1Application {
    public static void main(String[] args) {
        SpringApplication.run(Consumer1Application.class, args);
    }

//    @Bean
//    public Consumer<Event<Integer, Product>> consumer1() {
//        return event -> {
//            System.out.println("Received: " + event.toString());
//        };
//    }
}
