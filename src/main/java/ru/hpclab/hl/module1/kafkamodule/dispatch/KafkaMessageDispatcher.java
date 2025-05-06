package ru.hpclab.hl.module1.kafkamodule.dispatch;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.kafkamodule.queue.EntityType;
import ru.hpclab.hl.module1.kafkamodule.queue.KafkaOperationMessage;
import ru.hpclab.hl.module1.kafkamodule.queue.OperationType;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.service.LikesService;
import ru.hpclab.hl.module1.service.PostService;
import ru.hpclab.hl.module1.service.UserService;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {
    private final UserService userService;
    private final PostService articleService;
    private final LikesService downloadService;
    private final ObjectMapper objectMapper;

    public void dispatch(KafkaOperationMessage msg) {
        EntityType entity = msg.getEntity();
        OperationType operation = msg.getOperation();

        switch (entity) {
            case USER -> handleUser(operation, msg.getPayload());
            case POST -> handlePost(operation, msg.getPayload());
            case LIKES -> handleLikes(operation, msg.getPayload());
        }
    }

    private void handleUser(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> userService.saveUser(deserialize(payload, User.class));
            case PUT -> userService.saveUser(deserialize(payload, User.class)); // или реализовать update если нужно
            case DELETE -> userService.deleteUser(payload.get("id").asText());
            case CLEAR -> userService.clearAllUsers();
        }
    }

    private void handlePost(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> articleService.savePost(deserialize(payload, Post.class));
            case PUT -> articleService.savePost(deserialize(payload, Post.class)); // или update
            case DELETE -> articleService.deletePost(payload.get("id").asText());
            case CLEAR -> articleService.clearAllPosts();
        }
    }

    private void handleLikes(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> downloadService.saveLikes(deserialize(payload, Likes.class));
            case PUT -> downloadService.saveLikes(deserialize(payload, Likes.class)); // или update
            case DELETE -> downloadService.deleteLikes(payload.get("id").asText());
            case CLEAR -> downloadService.clearAllLikes();
        }
    }

    private <T> T deserialize(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload to " + clazz.getSimpleName(), e);
        }
    }
}