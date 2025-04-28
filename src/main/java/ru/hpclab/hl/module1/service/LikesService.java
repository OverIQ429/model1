package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.repository.JpaLikesRepository;

import java.util.*;

import ru.hpclab.hl.module1.repository.JpaPostRepository;
import ru.hpclab.hl.module1.model.Post;
@Service
@RequiredArgsConstructor
public class LikesService {

    private final ObservabilityService observability;
    private static final Logger logger = LoggerFactory.getLogger(LikesService.class);
    private final JpaLikesRepository likesRepository;

    private static final Map<UUID, List<UUID>> selfLikesMap = new HashMap<>();

    public void clearAllLikes() {
        likesRepository.deleteAll();
    }

    public LikesService(JpaLikesRepository likesRepository, JpaPostRepository postRepository, ObservabilityService observability) {
        this.likesRepository = likesRepository;
        this.observability = observability;
    }

    public List<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

    public Likes getLikesById(String id) {
        long start = System.nanoTime();
        try {
            return likesRepository.findById(UUID.fromString(id)).orElse(null);
        } finally {
            observability.recordTiming("LikesService.getLikesById", System.nanoTime() - start);
        }
    }

    public Likes saveLikes(Likes likes) {
        return likesRepository.save(likes);
    }

    public void deleteLikes(String id) {
        likesRepository.deleteById(UUID.fromString(id));
    }

    public Likes updateLikes(String id, Likes likes) {
        likes.setIdentifier(UUID.fromString(id));
        return likesRepository.save(likes);
    }

//    public Map<UUID, List<UUID>> get_selfLikes(UUID userId, UUID postId) {
//        // Проверяем, существует ли уже лайк от этого пользователя на этот пост
//        Post post = postRepository.findById(postId).orElse(null);
//        if (post.getOwner().equals(userId)) {
//            logger.warn("Пользователь {} поставил лайк своему собственному посту {}", userId, postId);
//            selfLikesMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(postId);
//        }
//        Likes like = new Likes();
//        like.setUserId(userId);
//        like.setPostId(postId);
//        likesRepository.save(like);
//
//        // Возвращаем текущую карту самолайков
//        return selfLikesMap;
//    }
}
