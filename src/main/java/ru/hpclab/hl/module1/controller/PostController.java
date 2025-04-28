package ru.hpclab.hl.module1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.service.ObservabilityService;
import ru.hpclab.hl.module1.service.PostService;
import java.util.Map;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class PostController {
    ObservabilityService observabilityService;

    @DeleteMapping("/posts/clear")
    public void clearAllPosts() {
        postService.clearAllPosts();
    }
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        long start = System.nanoTime();
        try {
            return postService.getAllPosts();}
        finally{
            observabilityService.recordTiming("postcontroller.getPosts",
                    System.nanoTime() - start);
        }
    }

    @GetMapping("/posts/stats/{operation}")
    public Map<String, Map<String, Number>> getStats(
            @PathVariable String operation) {
        return observabilityService.getStatistics(operation);
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        long start = System.nanoTime();
        try {
            return postService.getPostById(id);
        }finally{
            observabilityService.recordTiming("postcontroller.getPostById",
                    System.nanoTime() - start);
        }
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }

    @PostMapping(value = "/posts/")
    public Post savePost(@RequestBody Post post) {
        long start = System.nanoTime();
        try {
        return postService.savePost(post);}
        finally{
            observabilityService.recordTiming("postcontroller.savePost",
                    System.nanoTime() - start);
        }
    }

    @PutMapping(value = "/posts/{id}")
    public Post updatePost(@PathVariable(required = false) String id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

}
