package com.example.post_service.execption;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private LocalDateTime localDateTime;
    private HttpStatus status;
    private String message;
    private String path;
    private String developerMessage;
    private String method;
}
