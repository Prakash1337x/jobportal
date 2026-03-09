package com.example.jobportal.controller;

import com.example.jobportal.dto.ApplicationRequestDTO;
import com.example.jobportal.dto.ApplicationResponseDTO;
import com.example.jobportal.service.ApplicationService;
import com.example.jobportal.service.JobService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import com.example.jobportal.dto.JobResponseDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {
    private final ApplicationService applicationService;
    private final JobService jobService;

    public JobSeekerController(ApplicationService applicationService,  JobService jobService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
    }

    //Apply for job
    @PostMapping("/apply")
    public ApplicationResponseDTO applyJob(@RequestBody ApplicationRequestDTO dto){
        return applicationService.applyJob(dto);
    }

    //View Applied jobs
    @GetMapping("/application/{userId}")
    public List<ApplicationResponseDTO> getAppliedJobs(@PathVariable Long userId){
        return applicationService.getUserApplications(userId);
    }

    // View all jobs
    @GetMapping("/job")
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Search by job title
    @GetMapping("/search/title")
    public List<JobResponseDTO> searchByTitle(@RequestParam String title) {
        return jobService.searchByTitle(title);
    }

    // Search by location
    @GetMapping("/search/location")
    public List<JobResponseDTO> searchByLocation(@RequestParam String location){
        return jobService.searchByLocation(location);
    }

    // Search by salary
    @GetMapping("/search/salary")
    public List<JobResponseDTO> searchBySalary(@RequestParam Double salary){
        return jobService.searchBySalary(salary);
    }

    // Search by experience
    @GetMapping("/search/experience")
    public List<JobResponseDTO> searchByExperience(@RequestParam int experience){
        return jobService.searchByExperience(experience);
    }

    // Search by job type
    @GetMapping("/search/jobtype")
    public List<JobResponseDTO> searchByJobType(@RequestParam(name = "jobtype") String jobType){
        return jobService.saerchByJobType(jobType);
    }
}
