package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;

    public void clearAllPosts() {
        postRepository.deleteAll();
    }
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String id) {
        return postRepository.findById(UUID.fromString(id));
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(String id) {
        postRepository.delete(UUID.fromString(id));
    }

    public Post updatePost(String id, Post post) {
        post.setIdentifier(UUID.fromString(id));
        return postRepository.put(post);
    }
}
