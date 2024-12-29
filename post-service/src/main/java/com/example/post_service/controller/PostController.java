package com.example.post_service.controller;


import com.example.post_service.ContextHolder.UserContextHolder;
import com.example.post_service.payload.PostCreateRequestDto;
import com.example.post_service.payload.PostDto;
import com.example.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;

    }

    @PostMapping(path = "/createPost1")
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest)
    {

        Long currentUserId = UserContextHolder.getCurrentUserId();
        PostDto post = postService.createPost(postCreateRequestDto, currentUserId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);

    }
    @PostMapping(path = "/createPost2")
    public ResponseEntity<PostDto> createPostBy2(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest)
    {
        PostDto post = postService.createPost(postCreateRequestDto, 2L);
        return new ResponseEntity<>(post, HttpStatus.CREATED);

    }

    @GetMapping(path = "/getAllPosts")
    public ResponseEntity<List<PostDto>> getAll()
    {
        List<PostDto> posts= postService.fetchAll();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping(path = "postId/{postId}")
    public ResponseEntity<PostDto> getById(@PathVariable(name = "postId") Long id)
    {


        PostDto post= postService.fetchById(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping(path = "userId/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Long userId)
    {
        List<PostDto> posts= postService.fetchAllByUSerId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }



}
