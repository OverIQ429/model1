package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.Entity.UserEntity;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.service.UserService;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable UserEntity id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UserEntity id) {
        userService.deleteUser(id);
    }

    @PostMapping(value = "/users/")
    public User saveUser(@RequestBody User client) {
        return userService.saveUser(client);
    }

    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable(required = false)
                               UserEntity id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
