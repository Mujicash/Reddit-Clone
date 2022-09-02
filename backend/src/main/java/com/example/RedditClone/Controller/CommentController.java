package com.example.RedditClone.Controller;

import com.example.RedditClone.Dto.CommentRequest;
import com.example.RedditClone.Dto.CommentResponse;
import com.example.RedditClone.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRequest commentRequest) {
        commentService.createComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created.");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getPostComments(@PathVariable Long postId) {
        List<CommentResponse> postComments = commentService.getPostComments(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postComments);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CommentResponse>> getUserComments(@PathVariable String username) {
        List<CommentResponse> userComments = commentService.getUserComments(username);
        return ResponseEntity.status(HttpStatus.OK).body(userComments);
    }
}
