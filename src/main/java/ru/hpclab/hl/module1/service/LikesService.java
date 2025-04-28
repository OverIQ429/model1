package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.repository.JpaLikesRepository;

import java.util.*;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;
import ru.hpclab.hl.module1.repository.JpaPostRepository;
import ru.hpclab.hl.module1.model.Post;
@Service
@RequiredArgsConstructor
public class LikesService {

    private final ObservabilityService observabilityService;
    private static final Logger logger = LoggerFactory.getLogger(LikesService.class);
    private final JpaLikesRepository likesRepository;

    private static final Map<UUID, List<UUID>> selfLikesMap = new HashMap<>();

    public void clearAllLikes() {
        likesRepository.deleteAll();
    }
    @Autowired
    public LikesService(JpaLikesRepository likesRepository, JpaPostRepository postRepository, ObservabilityService observabilityService) {
        this.likesRepository = likesRepository;
        this.observabilityService = observabilityService;
    }

    public List<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

    public Likes getLikesById(String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Likes term = likesRepository.findById(UUID.fromString(id)).orElse(null);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;

    }

    public Likes saveLikes(Likes likes) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Likes term =  likesRepository.save(likes);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
    }

    public void deleteLikes(String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        likesRepository.deleteById(UUID.fromString(id));
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
    }

    public Likes updateLikes(String id, Likes likes) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        likes.setIdentifier(UUID.fromString(id));
        Likes term = likesRepository.save(likes);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
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
