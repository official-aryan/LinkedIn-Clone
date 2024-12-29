package com.example.user_service.service;

import com.example.user_service.entity.User;

import com.example.user_service.payload.LoginRequestDto;
import com.example.user_service.payload.SignupRequestDto;
import com.example.user_service.payload.UserDto;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.utils.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public UserDto signup(SignupRequestDto signupRequestDto) {

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashpassword(signupRequestDto.getPassword()));
        User save = userRepository.save(user);
        return modelMapper.map(save,UserDto.class);
    }


    public String login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email Not found"));

        if (PasswordUtil.checkpw(loginRequestDto.getPassword(),user.getPassword()))
        {
            return jwtService.generateToken(user);
        }
        else {

            throw new RuntimeException("INCORRECT PASSWORD");
        }
    }
}
