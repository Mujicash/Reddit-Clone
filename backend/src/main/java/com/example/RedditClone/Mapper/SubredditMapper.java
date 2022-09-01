package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.SubredditDto;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SubredditMapper {

    private final UserRepository userRepository;

    public SubredditMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Subreddit mapDtoToSubreddit(@NotNull SubredditDto subredditDto) {
        Subreddit subreddit = new Subreddit();
        subreddit.setName(subredditDto.getName());
        subreddit.setDescription(subredditDto.getDescription());
        subreddit.setCreated(Instant.now());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
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
