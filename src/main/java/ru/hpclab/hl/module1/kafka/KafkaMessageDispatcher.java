package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.service.UserService;
import ru.hpclab.hl.module1.service.LikesService;
import ru.hpclab.hl.module1.service.PostService;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {
    private final PostService postService;
    private final LikesService likesservice;
    private final UserService userservice;
    private final ObjectMapper objectMapper;

    public void dispatch(KafkaMessage message) {
        switch (message.getEntity()) {
            case LIKES -> handleLikes(message);
            case POST -> handlePost(message);
            case USER -> handleUser(message);
        }
    }

    private void handleLikes(KafkaMessage message) {
        Likes dto = deserializePayload(message.getPayload(), Likes.class);
        switch (message.getOperation()) {
            case CREATE -> likesservice.saveLikes(dto);
            case UPDATE -> likesservice.saveLikes(dto);
            case CLEAR -> likesservice.clearAllLikes();
        }
    }

    private void handlePost(KafkaMessage message) {
        Post dto = deserializePayload(message.getPayload(), Post.class);
        switch (message.getOperation()) {
            case CREATE -> postService.savePost(dto);
            case UPDATE -> postService.savePost(dto);
            case CLEAR -> postService.clearAllPosts();
        }
    }

    private void handleUser(KafkaMessage message) {
        User dto = deserializePayload(message.getPayload(), User.class);
        switch (message.getOperation()) {
            case CREATE -> userservice.saveUser(dto);
//            case UPDATE -> throw new UnsupportedOperationException("Booking update not supported");
//            case DELETE -> throw new UnsupportedOperationException("Booking delete not supported");
            case CLEAR -> userservice.clearAllUsers();
        }
    }

    private <T> T deserializePayload(JsonNode payload, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(payload, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload", e);
        }
    }
}