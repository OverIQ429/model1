package ru.hpclab.hl.module1.service.kafka;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.*;
import ru.hpclab.hl.module1.service.LikesService;
import ru.hpclab.hl.module1.service.PostService;
import ru.hpclab.hl.module1.service.UserService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@Service
public class KafkaMessageHandler {
    private final UserService userService;
    private final PostService postService;
    private final LikesService likesService;
    private final ObservabilityService observabilityService;

    public KafkaMessageHandler(UserService userService, PostService postService,
                               LikesService likesService, ObservabilityService observabilityService) {
        this.userService = userService;
        this.postService = postService;
        this.likesService = likesService;
        this.observabilityService = observabilityService;
    }

    public void handleMessage(KafkaMessage message) {
        String operationName = "KafkaMessageHandler:" + message.getOperation();
        observabilityService.start(operationName);

        try {
            switch (message.getEntityType()) {
                case USER -> handleUserMessage((UserKafkaMessage) message);
                case POST -> handlePostMessage((PostKafkaMessage) message);
                case LIKES -> handleLikesMessage((LikesKafkaMessage) message);
                default -> throw new IllegalArgumentException("Unknown entity type: " + message.getEntityType());
            }
        } finally {
            observabilityService.stop(operationName);
        }
    }

    private void handleUserMessage(UserKafkaMessage message) {
        User user = message.getPayload();
        switch (message.getOperation()) {
            case CREATE -> userService.saveUser(user);
            case UPDATE -> userService.updateUser(user.getIdentifier().toString(), user);
            case DELETE -> userService.deleteUser(user.getIdentifier().toString());
        }
    }

    private void handlePostMessage(PostKafkaMessage message) {
        Post post = message.getPayload();
        switch (message.getOperation()) {
            case CREATE -> postService.savePost(post);
            case UPDATE -> postService.updatePost(post.getIdentifier().toString(), post);
            case DELETE -> postService.deletePost(post.getIdentifier().toString());
        }
    }

    private void handleLikesMessage(LikesKafkaMessage message) {
        Likes likes = message.getPayload();
        switch (message.getOperation()) {
            case CREATE -> likesService.saveLikes(likes);
            case UPDATE -> likesService.updateLikes(likes.getIdentifier().toString(), likes);
            case DELETE -> likesService.deleteLikes(likes.getIdentifier().toString());
        }
    }
}