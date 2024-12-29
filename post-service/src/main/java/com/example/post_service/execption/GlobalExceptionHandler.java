package com.example.post_service.execption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> ResourceNotFoundExceptionHandler(ResourceNotFoundException e, WebRequest request, HttpServletRequest httpServletRequest)
    {
        ApiError resourceNotFoundException = ApiError.builder()
                .localDateTime(LocalDateTime.now())
                .path(request.getDescription(false))
                .method(httpServletRequest.getMethod())
                .developerMessage(e.getLocalizedMessage())
                .message("ResourceNotFoundException")
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(resourceNotFoundException,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> BadRequestExceptionHandler(BadRequestException e, WebRequest request, HttpServletRequest httpServletRequest)
    {
        ApiError BadRequestException = ApiError.builder()
                .localDateTime(LocalDateTime.now())
                .path(request.getDescription(false))
                .method(httpServletRequest.getMethod())
                .developerMessage(e.getLocalizedMessage())
                .message("BadRequestException")
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(BadRequestException,HttpStatus.BAD_REQUEST);
    }

}
