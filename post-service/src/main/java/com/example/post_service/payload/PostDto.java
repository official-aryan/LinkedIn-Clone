package com.example.post_service.payload;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;
    private String context;
    private Long userId;
    private LocalDateTime createdAt;
}
