package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.CommentRequest;
import com.example.RedditClone.Dto.CommentResponse;
import com.example.RedditClone.Model.Comment;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentRequest.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment mapToComment(CommentRequest commentRequest, Post post, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "postId", source = "comment.post.id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "username", source = "comment.user.username")
    @Mapping(target = "createdDate", source = "comment.createdDate")
    CommentResponse mapToDto(Comment comment);
}
