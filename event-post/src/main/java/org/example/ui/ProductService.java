package org.example.ui;

import org.example.dto.Product;
import org.example.event.Event;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductService {
    private final StreamBridge streamBridge;

    public ProductService(final StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping(value = "/product", consumes = "application/json", produces = "application/json")
    Product createProduct(@RequestBody Product body) {
        this.streamBridge.send("consumer1-out-0", new Event<>(Event.Type.CREATE, body.getProductId(), body));
        return body;
    }

    @DeleteMapping(value = "/product/{id}", consumes = "application/json", produces = "application/json")
    void deleteProduct(@PathVariable Long id) {
        this.streamBridge.send("consumer2-out-0", "PUSH!! - Delete : " + id);
    }
}
