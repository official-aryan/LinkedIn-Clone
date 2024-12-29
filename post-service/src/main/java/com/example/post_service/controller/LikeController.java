package com.example.post_service.controller;

import com.example.post_service.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/likes")
public class LikeController {

    private final PostLikeService postLikeService;

    public LikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping(path = "/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId)
    {
        postLikeService.likePost(1L,postId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId)
    {
        postLikeService.unlikePost(1L,postId);
        return ResponseEntity.noContent().build();
    }
}
