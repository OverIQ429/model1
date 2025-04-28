package ru.hpclab.hl.module1.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.hl.module1.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import ru.hpclab.hl.module1.repository.JpaPostRepository;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;
@Service
public class PostService {

    private final ObservabilityService observabilityService;
    private final JpaPostRepository postRepository;

    @Transactional
    public void clearAllPosts() {
        postRepository.deleteAll();
    }
    @Autowired
    public PostService(ObservabilityService observability, JpaPostRepository postRepository) {
        this.observabilityService = observability;
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        List<Post> term = postRepository.findAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
    }

    public Post getPostById(String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Post term = postRepository.findById(UUID.fromString(id)).orElse(null);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
    }

    public Post savePost(Post post) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Post term =  postRepository.save(post);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
    }

    public void deletePost(String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        postRepository.deleteById(UUID.fromString(id));
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
    }

    public Post updatePost(String id, Post post) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        post.setIdentifier(UUID.fromString(id));
        Post term = postRepository.save(post);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
    }
}
