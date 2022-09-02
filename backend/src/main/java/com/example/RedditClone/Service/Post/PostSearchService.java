package com.example.RedditClone.Service.Post;

import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Mapper.PostMapper;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostSearchService {

    private final PostRepository repository;
    private final PostMapper     mapper;


    public PostSearchService(PostRepository repository, PostMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    public PostResponse getPostById(Long id) {
        Post post = getPost(id);

        return mapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return repository.findById(id).orElseThrow(() -> new SpringRedditException("Post not found with id " + id));
    }

    @Transactional(readOnly = true)
    public PostResponse getPostByName(String name) {
        Post post = repository.findByPostName(name)
                              .orElseThrow(() -> new SpringRedditException("Post not found with name " + name));

        return mapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return repository.findAll().stream().map(mapper::mapToDto).collect(Collectors.toList());
    }
}
