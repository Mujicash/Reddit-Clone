package com.example.RedditClone.Model;

import com.example.RedditClone.Exception.SpringRedditException;

import java.util.Arrays;

public enum VoteType {

    UPVOTE(1), DOWNVOTE(-1);

    private final int direction;

    VoteType(int direction) {
        this.direction = direction;
    }

    public Integer getDirection() {
        return direction;
    }

    public static void main(String[] args) {
        System.out.println(VoteType.DOWNVOTE.getDirection());
    }
}
