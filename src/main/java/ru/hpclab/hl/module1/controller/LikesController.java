package ru.hpclab.hl.module1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.service.LikesService;
import ru.hpclab.hl.module1.service.ObservabilityService;
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
        long start = System.nanoTime();
        try {
            return likesService.getAllLikes();}
        finally{
            observabilityService.recordTiming("likecontroller.getLikes",
                    System.nanoTime() - start);
        }
    }

    @GetMapping("{id}")
    public Likes getLikesById(@PathVariable String id) {
        long start = System.nanoTime();
        try {
        return likesService.getLikesById(id);}
        finally{
            observabilityService.recordTiming("likecontroller.getLikesById",
                    System.nanoTime() - start);}
    }

    @DeleteMapping("/{id}")
    public void deleteLikes(@PathVariable String id) {
        likesService.deleteLikes(id);
    }

    @DeleteMapping("/clear")
    public void clearAllLikes() {
        likesService.clearAllLikes();
    }

    @PostMapping(value = "/")
    public Likes saveLikes(@RequestBody Likes likes) {
        long start = System.nanoTime();
        try {
            return likesService.saveLikes(likes);}
        finally{
            observabilityService.recordTiming("likecontroller.saveLikes",
                    System.nanoTime() - start);
        }

    }

    @PutMapping(value = "/{id}")
    public Likes updateLikes(@PathVariable(required = false) String id, @RequestBody Likes likes) {
        return likesService.updateLikes(id, likes);
    }

    @GetMapping("/monitoring/stats/{operation}")
    @Operation(summary = "Get operation statistics")
    public Map<String, Map<String, Number>> getStats(
            @Parameter(description = "Operation name")
            @PathVariable String operation) {
        return observabilityService.getStatistics(operation);
    }
}
