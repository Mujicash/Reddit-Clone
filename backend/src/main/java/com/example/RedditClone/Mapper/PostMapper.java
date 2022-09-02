package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.PostRequest;
import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "id", expression = "java(0L)")
    @Mapping(target = "user", source = "user")
    Post mapToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "username", source = "user.username")
    PostResponse mapToDto(Post post);

}
