package org.example.ui;

import static org.example.event.Event.Type.CREATE;

import org.example.dto.Product;
import org.example.event.Event;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(ProductService.MessageSources.class)
@RestController
public class ProductService {
    private static final String PRE_URL_HTTP = "http://";

    private final MessageSources messageSources;

    public ProductService(final MessageSources messageSources) {
        this.messageSources = messageSources;
    }

    public interface MessageSources {

        String OUTPUT_CONSUMER_1 = "output-consumer1";
        String OUTPUT_CONSUMER_2 = "output-consumer2";

        @Output(OUTPUT_CONSUMER_1)
        MessageChannel output1Products();

        @Output(OUTPUT_CONSUMER_2)
        MessageChannel output2Products();
    }

    @PostMapping(value = "/product", consumes = "application/json", produces = "application/json")
    Product createProduct(@RequestBody Product body) {
        messageSources.output1Products()
                .send(MessageBuilder.withPayload(new Event(CREATE, body.getProductId(), body)).build());
        return body;
    }

    @DeleteMapping(value = "/product/{id}", consumes = "application/json", produces = "application/json")
    void deleteProduct(@PathVariable Long id) {
        messageSources.output2Products()
                .send(MessageBuilder.withPayload(new Event(CREATE, id, null)).build());
    }
}
