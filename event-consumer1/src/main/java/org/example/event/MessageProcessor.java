package org.example.event;

import java.util.function.Consumer;

import org.example.dto.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageProcessor {
    /**
     * @return Producer에서 입력된 데이터 타입을 반환 타입으로 둔다.
     */
    @Bean
    public Consumer<Event<Integer, Product>> createProduct() {
        return event -> log.info("Create Received: {}, {}", event.getKey(), event.toString());
    }
}
