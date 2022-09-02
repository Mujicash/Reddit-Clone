package com.example.RedditClone.Service;

import com.example.RedditClone.Dto.CommentRequest;
import com.example.RedditClone.Dto.CommentResponse;
import com.example.RedditClone.Mapper.CommentMapper;
import com.example.RedditClone.Model.Comment;
import com.example.RedditClone.Model.NotificationEmail;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.CommentRepository;
import com.example.RedditClone.Repository.UserRepository;
import com.example.RedditClone.Service.Post.PostSearchService;
import com.example.RedditClone.Service.User.SessionService;
import com.example.RedditClone.Service.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final PostSearchService postService;
    private final SessionService    sessionService;
    private final CommentMapper     mapper;
    private final MailService       mailService;
    private final UserService       userService;

    public CommentService(CommentRepository repository, PostSearchService postService, SessionService sessionService,
                          CommentMapper mapper, MailService mailService, UserService userService) {
        this.repository     = repository;
        this.postService    = postService;
        this.sessionService = sessionService;
        this.mapper         = mapper;
        this.mailService    = mailService;
        this.userService    = userService;
    }

    @Transactional
    public void createComment(CommentRequest commentRequest) {
        System.out.println(commentRequest);
        Post post = postService.getPost(commentRequest.getPostId());
        User user = sessionService.getCurrentUser();

        Comment comment = mapper.mapToComment(commentRequest, post, user);
        repository.save(comment);
        mailService.sendMail(new NotificationEmail(user.getUsername() + " commented on your post " + post.getPostName(),
                                                   post.getUser().getEmail(),
                                                   user.getUsername() + " commented on your post."));
    }

    @Transactional
    public List<CommentResponse> getPostComments(Long postId) {
        Post post = postService.getPost(postId);

        return repository.findByPost(post).stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public List<CommentResponse> getUserComments(String username) {
        System.out.println(1);
        User user = userService.getUserByUsername(username);
        System.out.println(user);
        return repository.findByUser(user).stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

}
