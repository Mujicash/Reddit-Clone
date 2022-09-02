package com.example.RedditClone.Controller;

import com.example.RedditClone.Dto.PostRequest;
import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Service.Post.PostRegistrationService;
import com.example.RedditClone.Service.Post.PostSearchService;
import com.example.RedditClone.Service.Post.PostSubredditService;
import com.example.RedditClone.Service.Post.PostUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostRegistrationService registrationService;
    private final PostSearchService searchService;
    private final PostSubredditService postSubredditService;
    private final PostUserService postUserService;


    public PostController(PostRegistrationService registrationService, PostSearchService searchService,
                          PostSubredditService postSubredditService, PostUserService postUserService) {
        this.registrationService  = registrationService;
        this.searchService        = searchService;
        this.postSubredditService = postSubredditService;
        this.postUserService      = postUserService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse post = registrationService.registerPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = searchService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        PostResponse post = searchService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
        List<PostResponse> posts = postSubredditService.getPosts(id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username) {
        List<PostResponse> posts = postUserService.getPosts(username);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
}
