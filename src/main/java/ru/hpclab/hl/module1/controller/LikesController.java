package ru.hpclab.hl.module1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.service.LikesService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {

    private final ObservabilityService observabilityService;
    private final LikesService likesService;

    @Autowired
    public LikesController(ObservabilityService observabilityService, LikesService likesService) {
        this.observabilityService = observabilityService;
        this.likesService = likesService;
    }

    @GetMapping()
    public List<Likes> getLikes() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        List<Likes> temp = likesService.getAllLikes();
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return temp;
    }




    @GetMapping("{id}")
    public Likes getLikesById(@PathVariable String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Likes term = likesService.getLikesById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;

    }

    @DeleteMapping("/{id}")
    public void deleteLikes(@PathVariable String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        likesService.deleteLikes(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
    }

    @DeleteMapping("/clear")
    public void clearAllLikes() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        likesService.clearAllLikes();
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
    }

    @PostMapping(value = "/")
    public Likes saveLikes(@RequestBody Likes likes) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Likes term = likesService.saveLikes(likes);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

    @PutMapping(value = "/{id}")
    public Likes updateLikes(@PathVariable(required = false) String id, @RequestBody Likes likes) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        Likes term = likesService.updateLikes(id, likes);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

}