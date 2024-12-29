package com.example.post_service.service;

import com.example.post_service.entity.Post;
import com.example.post_service.execption.ResourceNotFoundException;
import com.example.post_service.payload.PostCreateRequestDto;
import com.example.post_service.payload.PostDto;
import com.example.post_service.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, long userId) {

        log.info("Started To create a Post");
        Post posts = modelMapper.map(postCreateRequestDto, Post.class);
        posts.setUserId(userId);
        Post save = postRepository.save(posts);
        log.info("Post Created");
        return modelMapper.map(save,PostDto.class);



    }

    public List<PostDto> fetchAll() {
        List<Post> all = postRepository.findAll();
        return all.stream().map(x-> modelMapper.map(x,PostDto.class)).toList();
    }


    public PostDto fetchById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID is not present" + id));
        return modelMapper.map(post,PostDto.class);
    }

    public List<PostDto> fetchAllByUSerId(Long userId) {

        List<Post> postByUserid=postRepository.findByUserId(userId);
        return postByUserid.stream()
                .map((element) -> modelMapper.map(element, PostDto.class))
                .toList();

    }
}