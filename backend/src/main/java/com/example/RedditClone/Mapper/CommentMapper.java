package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.CommentRequest;
import com.example.RedditClone.Dto.CommentResponse;
import com.example.RedditClone.Model.Comment;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentRequest.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    public abstract Comment mapToComment(CommentRequest commentRequest, Post post, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "postId", source = "comment.post.id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "username", source = "comment.user.username")
    @Mapping(target = "duration", expression = "java(getDuration(comment))")
    public abstract CommentResponse mapToDto(Comment comment);

    String getDuration(Comment comment) {
        return TimeAgo.using(comment.getCreatedDate().toEpochMilli());
    }
}
