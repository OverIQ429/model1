package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;
import ru.hpclab.hl.module1.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
public class UserController {
    private final ObservabilityService observabilityService;
    private final UserService userService;

    @Autowired
    public UserController(ObservabilityService observabilityService, UserService userService) {
        this.observabilityService = observabilityService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        List<User> term = userService.getAllUsers();
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable UUID id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        User term = userService.getUserById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        userService.deleteUser(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
    }

    @PostMapping(value = "/users/")
    public User saveUser(@RequestBody User client) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        User term = userService.saveUser(client);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }


    @DeleteMapping("/users/clear")
    public void clearAllUsers() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        userService.clearAllUsers();
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
    }
    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable(required = false) String id, @RequestBody User user) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        User term = userService.updateUser(id, user);
        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return term;
    }


}