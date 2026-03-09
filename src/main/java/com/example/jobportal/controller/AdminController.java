package com.example.jobportal.controller;

import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final JobService jobService;

    public AdminController(UserService userService, JobService jobService) {
        this.userService = userService;
        this.jobService = jobService;
    }

    //view users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Remove fake jobs
    @DeleteMapping("/job/{jobId}")
    public void removeFakeJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
    }
}
