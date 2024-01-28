package com.dipesh.instagram.controller;

import com.dipesh.instagram.models.User;
import com.dipesh.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @Autowired
    UserService userService;

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
}