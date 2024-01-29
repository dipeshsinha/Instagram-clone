package com.dipesh.instagram.service;

import com.dipesh.instagram.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createNewUser(User newUser);
    User getUserById(Integer id);

    User getUserByEmail(String email);
    String followUser(Integer userId1, Integer userId2);
    User updateUser(User user, Integer id);
    String deleteUser(Integer id);
    List<User> searchUser(String query);
}
