package com.example.RedditClone.Service.Post;

import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Mapper.PostMapper;
import com.example.RedditClone.Mapper.SubredditMapper;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Repository.PostRepository;
import com.example.RedditClone.Service.SubredditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostSubredditService {

    private final PostRepository   repository;
    private final SubredditService subredditService;
    private final PostMapper       mapper;


    public PostSubredditService(PostRepository repository, SubredditService subredditService, PostMapper mapper) {
        this.repository       = repository;
        this.subredditService = subredditService;
        this.mapper           = mapper;
    }


    @Transactional(readOnly = true)
    public List<PostResponse> getPosts(Long subredditId) {
        Subreddit  subreddit = subredditService.getSubreddit(subredditId);
        List<Post> posts     = repository.findBySubreddit(subreddit);

        return posts.stream().map(mapper::mapToDto).collect(Collectors.toList());
    }
}
