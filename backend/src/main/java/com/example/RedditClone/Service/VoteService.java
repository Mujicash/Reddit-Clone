package com.example.RedditClone.Service;

import com.example.RedditClone.Dto.VoteDto;
import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Mapper.VoteMapper;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Model.Vote;
import com.example.RedditClone.Model.VoteType;
import com.example.RedditClone.Repository.VoteRepository;
import com.example.RedditClone.Service.Post.PostSearchService;
import com.example.RedditClone.Service.User.SessionService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteService {

    private final static Logger logger = LoggerFactory.getLogger(VoteService.class);

    private final VoteRepository    repository;
    private final PostSearchService postService;
    private final SessionService    sessionService;
    private final VoteMapper        mapper;


    public VoteService(VoteRepository repository, PostSearchService postService, SessionService sessionService,
                       VoteMapper mapper) {
        this.repository     = repository;
        this.postService    = postService;
        this.sessionService = sessionService;
        this.mapper         = mapper;
    }

    @Transactional
    public void vote(@NotNull VoteDto voteDto) {
        Post post = postService.getPost(voteDto.getPostId());
        User user = sessionService.getCurrentUser();

        Optional<Vote> vote = repository.findByPostAndUser(post, user);

        if(!vote.isPresent()) {
            logger.info("Creando voto");
            repository.save(mapper.mapToVote(voteDto, post, user));
        } else if(vote.get().getVoteType().equals(voteDto.getVoteType().getDirection())) {
            logger.error("Ya se realizo un voto con ese valor");
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        } else {
            logger.info("Actualizando voto");
            Vote updatedVote = vote.get();
            updatedVote.setVoteType(voteDto.getVoteType().getDirection());
            repository.save(updatedVote);
        }
    }

}
