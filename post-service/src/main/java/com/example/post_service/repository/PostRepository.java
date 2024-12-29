package com.example.post_service.repository;

import com.example.post_service.entity.Post;
import com.example.post_service.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
}
