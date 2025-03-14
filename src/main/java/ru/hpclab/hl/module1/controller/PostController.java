package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.Entity.PostEntity;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable
                            PostEntity id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable
                               PostEntity id) {
        postService.deletePost(id);
    }

    @PostMapping(value = "/posts/")
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @PutMapping(value = "/posts/{id}")
    public Post updatePost(@PathVariable(required = false)
                               PostEntity id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

}
