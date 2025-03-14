package ru.hpclab.hl.module1.service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.Entity.LikeEntity;
import ru.hpclab.hl.module1.Entity.PostEntity;
import ru.hpclab.hl.module1.Entity.UserEntity;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.repository.LikesRepository;
import ru.hpclab.hl.module1.repository.PostRepository;
import ru.hpclab.hl.module1.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikesService(LikesRepository likesRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Likes> getAllLikes() {
        List<LikeEntity> likeEntities = likesRepository.findAll();
        return likeEntities.stream()
                .map(this::convertToLikes)
                .collect(Collectors.toList());
    }

    public Likes getLikesById(UUID id) {
        Optional<LikeEntity> optionalLikeEntity = likesRepository.findById(id);
        return optionalLikeEntity.map(this::convertToLikes).orElse(null);
    }

    public Likes saveLikes(Likes likes) {
        LikeEntity likeEntity = convertToLikeEntity(likes);
        LikeEntity savedLikeEntity = likesRepository.save(likeEntity);
        return convertToLikes(savedLikeEntity);
    }

    public void deleteLikes(UUID id) {
        likesRepository.deleteById(id);
    }
    @Transactional
    public Likes updateLikes(UUID id, Likes likes) {
        Optional<LikeEntity> optionalLikeEntity = likesRepository.findById(id);

        if (optionalLikeEntity.isPresent()) {
            LikeEntity existingLikeEntity = optionalLikeEntity.get();

            // Fetch UserEntity and PostEntity based on IDs from Likes model
            UserEntity user = userRepository.findById(likes.getUserId()).orElse(null);
            PostEntity post = postRepository.findById(likes.getUserPostId()).orElse(null);
            existingLikeEntity.setId(id);
            existingLikeEntity.setUser(user);
            existingLikeEntity.setPostOwner(post);
            existingLikeEntity.setPublicationDate(LocalDateTime.now());

            LikeEntity updatedLikeEntity = likesRepository.save(existingLikeEntity);
            return convertToLikes(updatedLikeEntity);
        } else {
            return null;
        }
    }

    private Likes convertToLikes(LikeEntity likeEntity) {
        Likes likes = new Likes();

        if (likeEntity.getId() != null)
            likes.setId(likeEntity.getId());

        if (likeEntity.getUser() != null) {
            likes.setUserId(likeEntity.getUser());
        }
        if (likeEntity.getPostOwner() != null) {
            likes.setUserPostId(likeEntity.getPostOwner());
        }
        if (likeEntity.getPublicationDate() != null)
            likes.setPublicationDate(likeEntity.getPublicationDate());
        CheckOwner(likes);


        return likes;
    }

    private Likes CheckOwner(Likes like) {
        UserEntity User = like.getUserId();
        PostEntity Post = like.getUserPostId();
        UserEntity Owner = Post.getOwner();
        if (User == Owner) {
            User.setSelfLikes(1);
            return like;
        }
        return like;
    }

    private LikeEntity convertToLikeEntity(Likes likes) {
        LikeEntity likeEntity = new LikeEntity();

        // Fetch UserEntity and PostEntity based on IDs from Likes model
        UserEntity user = userRepository.findById(likes.getUserId()).orElse(null);
        PostEntity post = postRepository.findById(likes.getUserPostId()).orElse(null);
        //likeEntity.setId(id);
        likeEntity.setUser(user);
        likeEntity.setPostOwner(post);
        likeEntity.setPublicationDate(LocalDateTime.now());
        return likeEntity;
    }
}

//package ru.hpclab.hl.module1.service;
//
//import org.springframework.stereotype.Service;
//import ru.hpclab.hl.module1.model.Likes;
//import ru.hpclab.hl.module1.model.Post;
//import ru.hpclab.hl.module1.repository.LikesRepository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class LikesService {
//
//    private final LikesRepository likesRepository;
//
//    public LikesService(LikesRepository likesRepository) {
//        this.likesRepository = likesRepository;
//    }
//
//    public List<Likes> getAllLikes() {
//        return likesRepository.findAll();
//    }
//
//    public Likes getLikesById(String id) {
//
//        Optional<Likes> optionalLikes = likesRepository.findById(UUID.fromString(id)); // Получаем Optional<Post>
//        if (optionalLikes.isPresent()) { // Проверяем, есть ли пост
//            return optionalLikes.get(); // Получаем Post из Optional
//        } else {
//            // Обрабатываем случай, когда пост не найден
//            // Например, можно выбросить исключение
//            throw new RuntimeException("Post with ID " + id + " not found");
//        }
//    }
//
//    public Likes saveLikes(Likes likes) {
//        return likesRepository.save(likes);
//    }
//
//    public void deleteLikes(String id) {
//        Optional<Likes> optionalLikes = likesRepository.findById(UUID.fromString(id));
//        if (optionalLikes.isPresent()) {
//            likesRepository.delete(optionalLikes.get()); // Удаляем объект Post
//        } else {
//            // Обрабатываем случай, когда пост не найден (например, выбрасываем исключение)
//            throw new RuntimeException("Post with ID " + id + " not found");
//        }
//    }
//
//    public Likes updateLikes(String id, Likes likes) {
//        UUID uuid = UUID.fromString(id);
//        Optional<Likes> optionalLikes = likesRepository.findById(uuid);
//
//        if (optionalLikes.isPresent()) {
//            Likes existingLikes = optionalLikes.get();
//
//            // Обновляем поля existingLikes данными из пришедшего likes
//            existingLikes.setUserId(likes.getUserId());
//            existingLikes.setUserPostId(likes.getUserPostId());
//
//            return likesRepository.save(existingLikes);
//        } else {
//            throw new RuntimeException("Like with ID " + id + " not found");
//        }
//    }
//


//        public Likes addLike (UUID userId, UUID userPostId){
//            if (likesRepository.findByUserIdAndUser_PostId(userId, userPostId).isPresent()) {
//                throw new IllegalStateException("User already liked this post");
//            }
//
//            Likes like = new Likes();
//            like.setUserId(userId);
//            like.setUserPostId(userPostId);
//
//            return likesRepository.save(like);
//        }
