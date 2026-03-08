package com.example.jobportal.repository;

import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //login
    Optional<User> findByEmail(String email);
    //check already exists
    boolean existsByEmail(String email);
    //admin view
    List<User> findByRole(Role role);
}
