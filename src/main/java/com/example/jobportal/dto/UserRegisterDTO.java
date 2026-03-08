package com.example.jobportal.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    @Email
    private String email;
    private String password;
    private String phone;
    private String role;
}
