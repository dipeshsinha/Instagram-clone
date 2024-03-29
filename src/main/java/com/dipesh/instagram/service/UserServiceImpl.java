package com.dipesh.instagram.service;


import com.dipesh.instagram.models.User;
import com.dipesh.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createNewUser(User user) {
        User newUser = new User();
        if(user.getId()!=null) {
            newUser.setId(user.getId());
        }
        if(user.getFirstName()!=null) {
            newUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null) {
            newUser.setLastName(user.getLastName());
        }if(user.getEmail()!=null) {
            newUser.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null) {
            newUser.setPassword(user.getPassword());
        }

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User getUserById(Integer id) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User with ID " + id +" does not exist.");
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String followUser(Integer userId1, Integer userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);


        if (user1.getFollowings() != null) {
            user1.getFollowings().add(userId2);
        }
        else {
            List temp = new ArrayList();
            temp.add(userId2);
            user1.setFollowings(temp);
        }

        if (user2.getFollowers() != null) {
            user2.getFollowers().add(userId1);
        } else {
            List temp = new ArrayList();
            temp.add(userId1);
            user2.setFollowers(temp);
        }

        userRepository.save(user1);
        userRepository.save(user2);

        return "User " + userId1 + " followed user " + userId2 + " successfully.";
    }

    @Override
    public User updateUser(User updatedUser, Integer id) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User savedUser = userRepository.save(updatedUser);
            return savedUser;
        }
        throw new UserNotFoundException("User with ID " + id +" does not exist.");
    }

    @Override
    public String deleteUser(Integer id) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User with ID " + id + " delete successfully";
        }
        throw new UserNotFoundException("User with ID " + id +" does not exist.");
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }


}
