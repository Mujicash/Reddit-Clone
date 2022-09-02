package com.example.RedditClone.Service.Post;

import com.example.RedditClone.Dto.PostRequest;
import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Mapper.PostMapper;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.PostRepository;
import com.example.RedditClone.Service.User.SessionService;
import com.example.RedditClone.Service.SubredditService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PostRegistrationService {

    private final PostRepository   repository;
    private final SubredditService subredditService;
    private final SessionService   sessionService;
    private final PostMapper       mapper;


    public PostRegistrationService(PostRepository repository, SubredditService subredditService,
                                   SessionService sessionService, PostMapper mapper) {
        this.repository       = repository;
        this.subredditService = subredditService;
        this.sessionService   = sessionService;
        this.mapper           = mapper;
    }

    public PostResponse registerPost(@NotNull PostRequest postRequest) {
        System.out.println("Select Subreddit");
        Subreddit subreddit   = subredditService.getByName(postRequest.getSubredditName());
        System.out.println("Select Current User");
        User      currentUser = sessionService.getCurrentUser();
        System.out.println("Map post");
        Post      newPost     = mapper.mapToPost(postRequest, subreddit, currentUser);

        System.out.println("Insert post");
        return mapper.mapToDto(repository.save(newPost));
    }


}
