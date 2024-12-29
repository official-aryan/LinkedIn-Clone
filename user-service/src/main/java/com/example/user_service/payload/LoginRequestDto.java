package com.example.user_service.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginRequestDto {


    private String email;
    private String password;
}
