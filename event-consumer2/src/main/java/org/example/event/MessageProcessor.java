package org.example.event;

import org.example.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class MessageProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

    @StreamListener(target = Sink.INPUT)
    public void process(Event<Integer, Product> event) {

        LOG.info("Consumer 2 :: Process message created at {}...", event.getEventCreatedAt());

        switch (event.getEventType()) {

            case CREATE:
                final Product data = event.getData();
                LOG.info("Consumer 2 :: Create Product with ID: {}/{}", data.getProductId(), data.getServiceAddress());
                break;

            case DELETE:
                int productId = event.getKey();
                LOG.info("Consumer 2 :: Delete Product with ProductID: {}", productId);
                break;

            default:
                String errorMessage = "Consumer 2 :: Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                LOG.warn(errorMessage);
                throw new IllegalArgumentException(errorMessage);
        }

        LOG.info("Message processing done!");
    }
}
