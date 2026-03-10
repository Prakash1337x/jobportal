package com.example.jobportal.controller;

import com.example.jobportal.dto.CompanyResponseDTO;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final JobService jobService;
    private final CompanyService companyService;

    public AdminController(UserService userService, JobService jobService,  CompanyService companyService) {
        this.userService = userService;
        this.jobService = jobService;
        this.companyService = companyService;
    }

    //Get users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Remove Users
    @DeleteMapping("/users/{userid}")
    public void deleteUser(@PathVariable Long userid) {
        userService.deleteUserById(userid);
    }

    // Get Employers
    @GetMapping("/employers")
    public List<User> getAllEmployers() {
        return userService.getEmployers();
    }

    // View CompanyBYEmpID
    @GetMapping("/company/{employerId}")
    public CompanyResponseDTO getCompanyByEmployerId(Long employerId) {
        return companyService.getCompanyByEmployerId(employerId);
    }

    // Remove fake jobs
    @DeleteMapping("/job/{jobId}")
    public void removeFakeJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
    }

    //View Job jobStatistics
    @GetMapping("/jobstats")
    public Map<String, Long> getJobStats() {
        return userService.jobStatistics();
    }
}
