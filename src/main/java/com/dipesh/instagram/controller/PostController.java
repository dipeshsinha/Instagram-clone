package com.dipesh.instagram.controller;

import com.dipesh.instagram.models.Post;
import com.dipesh.instagram.response.ApiResponse;
import com.dipesh.instagram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post postDetails, @PathVariable Integer userId) throws Exception {
        Post post = postService.createNewPost(postDetails , userId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        String message = postService.deletePost(postId, userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/users/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
}
