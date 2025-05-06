package ru.hpclab.hl.module1.kafkamodule.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.service.LikesService;
import ru.hpclab.hl.module1.service.PostService;
import ru.hpclab.hl.module1.service.UserService;
import ru.hpclab.hl.module1.kafkamodule.dispatch.KafkaMessageDispatcher;

@Configuration
public class KafkaDispatcherConfig {
    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            UserService userService,
            PostService articleService,
            LikesService downloadService,
            ObjectMapper objectMapper
    ) {
        return new KafkaMessageDispatcher(
                userService,
                articleService,
                downloadService,
                objectMapper
        );
    }
}