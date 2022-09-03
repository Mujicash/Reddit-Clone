package com.example.RedditClone.Dto;

import com.example.RedditClone.Model.VoteType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDto {

    private VoteType voteType;
    private Long postId;
}
