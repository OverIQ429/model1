package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.model.Likes;
import java.util.*;
import ru.hpclab.hl.module1.Entity.LikeEntity;

public interface LikesRepository extends JpaRepository<LikeEntity,UUID> {
    //Optional<Likes> findByUserIdAndUser_PostId(UUID userId, UUID userPostId);
}


//@Repository
//public class LikesRepository {
//
//    public static final String LIKES_NOT_FOUND_MSG = "Like with ID %s not found";
//    public static final String LIKES_EXISTS_MSG = "Like with ID %s is already exists";
//    private final Map<UUID, Likes> likes = new HashMap<>();
//
//
//    public List<Likes> findAll() {
//        return new ArrayList<>(likes.values());
//    }
//
//
//    public void delete(UUID id) {
//        final var removed = likes.remove(id);
//        if (removed == null) {
//            throw new UserException(format(LIKES_NOT_FOUND_MSG, id));
//        }
//    }
//
//    public Likes findById(UUID id) {
//        final var liked = likes.get(id);
//        if (liked == null) {
//            throw new UserException(format(LIKES_NOT_FOUND_MSG, id));
//        }
//        return liked;
//    }
//
//    public Likes save(Likes like) {
//        if (ObjectUtils.isEmpty(like.getIdentifier())) {
//            like.setIdentifier(UUID.randomUUID());
//        }
//
//        final var likesData = likes.get(like.getIdentifier());
//        if (likesData != null) {
//            throw new UserException(format(LIKES_EXISTS_MSG, like.getIdentifier()));
//        }
//
//        likes.put(like.getIdentifier(), like);
//
//        return like;
//    }
//    public Optional<Likes> findByUserIdAndPostId(UUID userId, UUID postId) {
//        return likes.values().stream()
//                .filter(like -> like.getUserId().equals(userId) && like.getPostId().equals(postId))
//                .findFirst();
//    }
//
//    public Likes put(Likes like) {
//        final var likesData = likes.get(like.getIdentifier());
//        if (likesData == null) {
//            throw new UserException(format(LIKES_NOT_FOUND_MSG, like.getIdentifier()));
//        }
//
//        final var removed = likes.remove(like.getIdentifier());
//        if (removed != null) {
//            likes.put(like.getIdentifier(), like);
//        } else {
//            throw new UserException(format(LIKES_NOT_FOUND_MSG, like.getIdentifier()));
//        }
//
//        return like;
//    }
//
//
//    public void clear(){
//        likes.clear();
//    }
//
//}
