package com.example.post_service.service;

import com.example.post_service.entity.PostLike;
import com.example.post_service.execption.BadRequestException;
import com.example.post_service.execption.ResourceNotFoundException;
import com.example.post_service.repository.PostLikeRepository;
import com.example.post_service.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public PostLikeService(PostLikeRepository postLikeRepository, PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
    }

    public void likePost(long userId,Long postId)
    {
        log.info("Attempting to like the post with id "+postId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) throw  new ResourceNotFoundException("Post Not Found "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) throw new BadRequestException("Post already Liked by "+userId);

        PostLike postLike=new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        PostLike save = postLikeRepository.save(postLike);
        log.info("post: "+postId+" is liked by "+userId);

    }

    public void unlikePost(long userId, Long postId) {
        log.info("Attempting to unlike the post with id "+postId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) throw  new ResourceNotFoundException("Post Not Found "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (!alreadyLiked) throw new BadRequestException("you cannot unlike a post which is not yet liked by you");

        postLikeRepository.deleteByUserIdAndPostId(userId,postId);

    }
}
