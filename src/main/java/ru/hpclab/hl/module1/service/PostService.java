package ru.hpclab.hl.module1.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.hl.module1.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import ru.hpclab.hl.module1.repository.JpaPostRepository;
@Service
public class PostService {

    private final ObservabilityService observability;
    private final JpaPostRepository postRepository;

    @Transactional
    public void clearAllPosts() {
        postRepository.deleteAll();
    }
    @Autowired
    public PostService(ObservabilityService observability, JpaPostRepository postRepository) {
        this.observability = observability;
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        long start = System.nanoTime();
        try {
            return postRepository.findAll();
        } finally {
            observability.recordTiming("PostService.getAllPosts", System.nanoTime() - start);
        }
    }

    public Post getPostById(String id) {
        long start = System.nanoTime();
        try {
            return postRepository.findById(UUID.fromString(id)).orElse(null);
        } finally {
            observability.recordTiming("PostService.getPostById", System.nanoTime() - start);
        }
    }

    public Post savePost(Post post) {
        long start = System.nanoTime();
        try {
            return postRepository.save(post);
        } finally {
            observability.recordTiming("PostService.savePost", System.nanoTime() - start);
        }
    }

    public void deletePost(String id) {
        postRepository.deleteById(UUID.fromString(id));;
    }

    public Post updatePost(String id, Post post) {
        post.setIdentifier(UUID.fromString(id));
        return postRepository.save(post);
    }
}
