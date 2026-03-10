package com.example.jobportal.service;

import com.example.jobportal.dto.UserRegisterDTO;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.BadRequestException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       JobRepository jobRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    //register
    public User register(UserRegisterDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists");
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

    //Delete Users
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //Get Employers
    public List<User> getEmployers() {
        return userRepository.findByRole(Role.EMPLOYER);
    }

    //jobStatistics
    public Map<String, Long> jobStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("Posted Jobs ", jobRepository.count());
        return stats;
    }
}
