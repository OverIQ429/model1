package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.service.ObservabilityService;
import ru.hpclab.hl.module1.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
public class UserController {
    ObservabilityService observabilityService;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/users/selflikes")
//    public List<Map<String, Object>> getselflikesUsers() {
//        return userService.getSelflikesUser();
//    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable UUID id) {
        long start = System.nanoTime();
        try {
            return userService.getUserById(id);}
        finally{
                observabilityService.recordTiming("usercontroller.getUserById",
                        System.nanoTime() - start);
            }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        long start = System.nanoTime();
        try {
            userService.deleteUser(id);}
        finally{
            observabilityService.recordTiming("usercontroller.deleteUser",
                    System.nanoTime() - start);
        }
    }

    @PostMapping(value = "/users/")
    public User saveUser(@RequestBody User client) {
        long start = System.nanoTime();
        try {
        return userService.saveUser(client);}
        finally{
            observabilityService.recordTiming("usercontroller.saveUser",
                    System.nanoTime() - start);
        }
    }


    @DeleteMapping("/users/clear")
    public void clearAllUsers() {
        long start = System.nanoTime();
        try {
        userService.clearAllUsers();}
        finally{
            observabilityService.recordTiming("usercontroller.clearAllUsers",
                    System.nanoTime() - start);
        }
    }
    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable(required = false) String id, @RequestBody User user) {
        long start = System.nanoTime();
        try {
        return userService.updateUser(id, user);}
        finally{
            observabilityService.recordTiming("usercontroller.updateUser",
                    System.nanoTime() - start);
        }
    }

    @GetMapping("/users/stats/{operation}")
    public Map<String, Map<String, Number>> getStats(
            @PathVariable String operation) {
        return observabilityService.getStatistics(operation);
    }

}
