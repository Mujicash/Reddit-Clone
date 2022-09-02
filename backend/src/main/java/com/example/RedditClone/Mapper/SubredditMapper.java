package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.SubredditDto;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Service.User.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SubredditMapper {

    private final SessionService sessionService;

    public SubredditMapper(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public Subreddit mapDtoToSubreddit(@NotNull SubredditDto subredditDto) {
        Subreddit subreddit = new Subreddit();
        subreddit.setId(subredditDto.getId());
        subreddit.setName(subredditDto.getName());
        subreddit.setDescription(subredditDto.getDescription());
        subreddit.setCreated(Instant.now());
        User user = sessionService.getCurrentUser();
        subreddit.setUser(user);

        return subreddit;
    }

    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        SubredditDto subredditDto = new SubredditDto();
        subredditDto.setId(subreddit.getId());
        subredditDto.setName(subreddit.getName());
        subredditDto.setDescription(subreddit.getDescription());
        subredditDto.setNumberOfPosts(subreddit.getPosts().size());

        return subredditDto;
    }
}
