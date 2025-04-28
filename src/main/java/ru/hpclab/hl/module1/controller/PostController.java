package ru.hpclab.hl.module1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.service.PostService;
import java.util.Map;
import java.util.List;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@RestController
@RequestMapping
public class PostController {
    private final ObservabilityService observabilityService;

    @DeleteMapping("/posts/clear")
    public void clearAllPosts() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        postService.clearAllPosts();
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
    }
    private final PostService postService;

    @Autowired
    public PostController(ObservabilityService observabilityService, PostService postService) {
        this.observabilityService = observabilityService;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        List<Post> term = postService.getAllPosts();
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Post term = postService.getPostById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        postService.deletePost(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
    }

    @PostMapping(value = "/posts/")
    public Post savePost(@RequestBody Post post) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Post term = postService.savePost(post);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

    @PutMapping(value = "/posts/{id}")
    public Post updatePost(@PathVariable(required = false) String id, @RequestBody Post post) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Post term = postService.updatePost(id, post);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

}
