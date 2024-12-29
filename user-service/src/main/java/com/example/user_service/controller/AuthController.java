package com.example.user_service.controller;

import com.example.user_service.payload.LoginRequestDto;
import com.example.user_service.payload.SignupRequestDto;
import com.example.user_service.payload.UserDto;
import com.example.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/login")
    public ResponseEntity<String > login(@RequestBody LoginRequestDto loginRequestDto)
    {
        String token=userService.login(loginRequestDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto)
    {
        UserDto userDto=userService.signup(signupRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
