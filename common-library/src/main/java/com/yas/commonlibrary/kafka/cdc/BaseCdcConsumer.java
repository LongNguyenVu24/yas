package com.yas.commonlibrary.kafka.cdc;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;

/**
 * Base class for CDC (Change Data Capture) Kafka consumers.
 * Provides common methods for processing messages and handling Dead Letter Topic (DLT) events.
 *
 * @param <T> Type of the message payload.
 */
public abstract class BaseCdcConsumer<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseCdcConsumer.class);

    protected void processMessage(T record, MessageHeaders headers, Consumer<T> consumer) {
        LOGGER.debug("## Received message - headers: {}", headers);
        if (record == null) {
            LOGGER.warn("## Null payload received");
        } else {
            LOGGER.debug("## Processing record - Key: {} | Value: {}", headers.get(KafkaHeaders.RECEIVED_KEY), record);
            consumer.accept(record);
            LOGGER.debug("## Record processed successfully - Key: {} \n", headers.get(KafkaHeaders.RECEIVED_KEY));
        }
    }
}
