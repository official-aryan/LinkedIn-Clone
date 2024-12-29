package com.example.user_service.payload;

import lombok.Data;

@Data
public class SignupRequestDto {

    private String name;
    private String email;
    private String password;
}
