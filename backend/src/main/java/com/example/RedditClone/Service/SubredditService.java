package com.example.RedditClone.Service;

import com.example.RedditClone.Dto.SubredditDto;
import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Mapper.SubredditMapper;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Repository.SubredditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubredditService {

    private final SubredditRepository repository;
    private final SubredditMapper     mapper;

    public SubredditService(SubredditRepository repository, SubredditMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Transactional
    public void save(SubredditDto subredditDto) {
        Subreddit subreddit = repository.save(mapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        subredditDto.setNumberOfPosts(0);
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return repository.findAll().stream().map(mapper::mapSubredditToDto).collect(Collectors.toList());
    }

    public SubredditDto getById(Long id) {
        Subreddit subreddit = getSubreddit(id);

        return mapper.mapSubredditToDto(subreddit);
    }

    @Transactional
    public Subreddit getSubreddit(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new SpringRedditException("There is no subreddit with the id " + id));
    }

    public Subreddit getByName(String name) {
        return repository.findByName(name)
                         .orElseThrow(() -> new SpringRedditException("There is no subreddit with the name " + name));
    }
}
