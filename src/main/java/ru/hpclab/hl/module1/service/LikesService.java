package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.repository.LikesRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LikesService {

    private final LikesRepository likesRepository;

    public void clearAllLikes() {
        likesRepository.deleteAll();
    }
    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public List<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

    public Likes getLikesById(String id) {
        return likesRepository.findById(UUID.fromString(id));
    }

    public Likes saveLikes(Likes likes) {
        return likesRepository.save(likes);
    }

    public void deleteLikes(String id) {
        likesRepository.delete(UUID.fromString(id));
    }

    public Likes updateLikes(String id, Likes likes) {
        likes.setIdentifier(UUID.fromString(id));
        return likesRepository.put(likes);
    }

    public Likes addLike(UUID userId, UUID postId) {
        // Проверяем, существует ли уже лайк от этого пользователя на этот пост
        if (likesRepository.findByUserIdAndPostId(userId, postId).isPresent()) {
            throw new IllegalStateException("User already liked this post"); // Или просто игнорируем
        }

        Likes like = new Likes();
        like.setUserId(userId);
        like.setPostId(postId);

        return likesRepository.save(like);
    }
}
