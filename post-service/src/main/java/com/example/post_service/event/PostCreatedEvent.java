package com.example.post_service.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreatedEvent {

    Long creatorId;
    String context;
    Long postId;
}
