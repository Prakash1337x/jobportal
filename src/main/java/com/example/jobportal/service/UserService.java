package com.example.jobportal.service;

import com.example.jobportal.dto.UserRegisterDTO;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    //register
    public User register(UserRegisterDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email alredy exists");
        }
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        System.out.println(dto);
        return userRepository.save(user);
    }

    //Manage users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> getEmployers() {
        return userRepository.findByRole(Role.EMPLOYER);
    }
}
