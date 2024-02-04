package com.dipesh.instagram.service;

import com.dipesh.instagram.models.Post;
import com.dipesh.instagram.models.User;
import com.dipesh.instagram.repository.PostRepository;
import com.dipesh.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.getUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        newPost.setCreatedAt(LocalDateTime.now());
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        if (post.getUser().getId().equals(userId)) {
            postRepository.delete(post);
            return "Post deleted successfully";
        } else {
            throw new UserNotFoundException("Post can be deleted only by the creator");
        }
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {

        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new UserNotFoundException("Post with ID " + postId + " does not exist.");
        }

        return post.get();
    }

    @Override
    public List<Post> findAllPost() {

        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.getUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.getUserById(userId);
        if(post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
