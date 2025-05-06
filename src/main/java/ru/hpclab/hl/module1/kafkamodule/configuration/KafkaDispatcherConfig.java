package ru.hpclab.hl.module1.kafkamodule.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.apimodule.service.ArticleService;
import ru.hpclab.hl.module1.apimodule.service.DownloadService;
import ru.hpclab.hl.module1.apimodule.service.UserService;
import ru.hpclab.hl.module1.kafkamodule.dispatch.KafkaMessageDispatcher;

@Configuration
public class KafkaDispatcherConfig {
    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            UserService userService,
            ArticleService articleService,
            DownloadService downloadService,
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