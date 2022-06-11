package org.example.event;

import java.util.function.Consumer;

import org.example.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
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
    public Consumer<String> deleteProduct() {
        return event -> log.info("Delete Received: {}", event);
    }
}
