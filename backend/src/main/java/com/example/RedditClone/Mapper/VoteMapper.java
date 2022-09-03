package com.example.RedditClone.Mapper;

import com.example.RedditClone.Dto.VoteDto;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "voteType", expression = "java(voteDto.getVoteType().getDirection())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Vote mapToVote(VoteDto voteDto, Post post, User user);
}
