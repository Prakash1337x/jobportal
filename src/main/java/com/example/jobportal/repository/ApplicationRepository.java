package com.example.jobportal.repository;

import com.example.jobportal.entity.Application;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findByJob(Job job);
    List<Application> findByJobSeeker(User user);
}
