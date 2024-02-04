package com.dipesh.instagram.controller;

import com.dipesh.instagram.models.User;
import com.dipesh.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    UserService userService;

    //Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Add a new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    //Find user by ID
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        return userService.getUserById(id);
    }


    //Update existing user
    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) {
        return userService.updateUser(user, userId);
    }

    //Delete existing user
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer id) {
        return userService.deleteUser(id);
    }

    //follow a user
    @PostMapping("/users/follow/{userId1}/{userId2}")
    public String followUser (@PathVariable("userId1") Integer userId1, @PathVariable("userId2") Integer userId2) {
        return userService.followUser(userId1, userId2);
    }

    //search User
    @GetMapping("users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }
}
