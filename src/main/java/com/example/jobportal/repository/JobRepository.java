package com.example.jobportal.repository;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    //By Title
    List<Job> findByTitleContainingIgnoreCase(String title);
    //By Loacation
    List<Job> findByLocationIgnoreCase(String location);
    //By Salary
    List<Job> findBySalaryGreaterThanEqual(Double salary);
    //By Exp
    List<Job> findByExperienceLessThanEqual(int experience);
    //By JobType
    List<Job> findByJobTypeIgnoreCase(String jobType);
}
