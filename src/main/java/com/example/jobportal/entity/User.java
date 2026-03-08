package com.example.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
