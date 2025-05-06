package ru.hpclab.hl.module1.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.hl.module1.model.KafkaMessage;

@Component
public class KafkaConsumer {
    private final KafkaMessageHandler messageHandler;

    public KafkaConsumer(KafkaMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Transactional
    @KafkaListener(
            topics = "${kafka.topic}",
            concurrency = "${kafka.concurrency}",
            groupId = "${kafka.groupId}"
    )
    public void listen(
            KafkaMessage message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        try {
            messageHandler.handleMessage(message);
        } catch (Exception e) {
            System.err.printf("Error processing message from topic %s partition %d: %s%n",
                    topic, partition, e.getMessage());
            throw e;
        }
    }
}