package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.PostRequest;
import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Model.Vote;
import com.example.RedditClone.Repository.CommentRepository;
import com.example.RedditClone.Repository.VoteRepository;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository    voteRepository;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "id", expression = "java(0L)")
    @Mapping(target = "user", source = "user")
    public abstract Post mapToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "voteCount", expression = "java(voteCount(post))")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    Integer voteCount(Post post) {
        List<Vote> votes = voteRepository.findByPost(post);
        Integer count = 0;

        for(Vote vote : votes) {
            count += vote.getVoteType();
        }

        return count;
    }
}
