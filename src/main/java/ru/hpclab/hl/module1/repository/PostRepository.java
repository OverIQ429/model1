package ru.hpclab.hl.module1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.model.Post;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.Entity.PostEntity;
import java.util.*;

import static java.lang.String.format;

public interface PostRepository extends JpaRepository<PostEntity, PostEntity> {
}

//@Repository
//public class PostRepository {
//
//    public static final String POST_NOT_FOUND_MSG = "Post with ID %s not found";
//    public static final String POST_EXISTS_MSG = "Post with ID %s is already exists";
//
//    private final Map<UUID, Post> posts = new HashMap<>();
//
//    public List<Post> findAll() {
//        return new ArrayList<>(posts.values());
//    }
//
//    public Post findById(UUID id) {
//        final var post = posts.get(id);
//        if (post == null) {
//            throw new UserException(format(POST_NOT_FOUND_MSG, id));
//        }
//        return post;
//    }
//
//    public void delete(UUID id) {
//        final var removed = posts.remove(id);
//        if (removed == null) {
//            throw new UserException(format(POST_NOT_FOUND_MSG, id));
//        }
//    }
//
//    public Post save(Post post) {
//        if (ObjectUtils.isEmpty(post.getIdentifier())) {
//            post.setIdentifier(UUID.randomUUID());
//        }
//
//        final var postData = posts.get(post.getIdentifier());
//        if (postData != null) {
//            throw new UserException(format(POST_EXISTS_MSG, post.getIdentifier()));
//        }
//
//        posts.put(post.getIdentifier(), post);
//
//        return post;
//    }
//
//    public Post put(Post post) {
//        final var postData = posts.get(post.getIdentifier());
//        if (postData == null) {
//            throw new UserException(format(POST_NOT_FOUND_MSG, post.getIdentifier()));
//        }
//
//        final var removed = posts.remove(post.getIdentifier());
//        if (removed != null) {
//            posts.put(post.getIdentifier(), post);
//        } else {
//            throw new UserException(format(POST_NOT_FOUND_MSG, post.getIdentifier()));
//        }
//
//        return post;
//    }
//
//    public void clear(){
//        posts.clear();
//    }
//
//}
