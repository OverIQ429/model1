package ru.hpclab.hl.module1.kafkamodule.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.kafkamodule.dispatch.KafkaMessageDispatcher;
import ru.hpclab.hl.module1.kafkamodule.queue.KafkaOperationMessage;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ObjectMapper objectMapper;
    private final KafkaMessageDispatcher kafkaMessageDispatcher;
    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    private final AtomicInteger messageCounter = new AtomicInteger(0);

    @KafkaListener(
            topics = "${kafka.topic:articles-users-downloads}",
            groupId = "${kafka.groupId:seryak0ff-consumer-group}",
            concurrency = "${kafka.concurrency:2}", // Можно настроить по количеству партиций
            containerFactory = "kafkaListenerContainerFactory"
    )

    public void handleMessage(@Payload List<String> messageJsonList, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        for (int i = 0; i < messageJsonList.size(); i++) {
            long offset = offsets.get(i);
            String message = messageJsonList.get(i);
            try {
                KafkaOperationMessage messageKafka = objectMapper.readValue(message, KafkaOperationMessage.class);

                if (offset % 500 == 0) {
                    log.info("500th message reached! Count: {}, Message: {}",
                            offset, message);
                }
                kafkaMessageDispatcher.dispatch(messageKafka);
            } catch (Exception e) {
                log.error("Error while parsing or dispatching Kafka message: {}", message, e);
            }
        }
    }




//    public void handleMessage(List<String> messageJsonList) {
//        for (String messageJson : messageJsonList) {
//            try {
//                KafkaOperationMessage message = objectMapper.readValue(messageJson, KafkaOperationMessage.class);
////                log.info("Received Kafka message: {}", message);
//                kafkaMessageDispatcher.dispatch(message);
//            } catch (Exception e) {
//                log.error("Error while parsing or dispatching Kafka message: {}", messageJson, e);
//            }
//        }
//    }
}