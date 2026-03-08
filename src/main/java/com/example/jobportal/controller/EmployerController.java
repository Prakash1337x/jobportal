package com.example.jobportal.controller;

import com.example.jobportal.dto.*;
import com.example.jobportal.entity.Application;
import com.example.jobportal.entity.ApplicationStatus;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.mapper.JobMapper;
import com.example.jobportal.service.ApplicationService;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employer")
public class EmployerController {
    private CompanyService companyService;
    private JobService jobService;
    private ApplicationService applicationService;

    public EmployerController(CompanyService companyService, JobService jobService,
                              ApplicationService applicationService) {
        this.companyService = companyService;
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    //Create Company
    @PostMapping("/company")
    public CompanyResponseDTO createCommapny(@RequestBody CompanyCreateDTO dto){
        return companyService.createCompany(dto);
    }

    //Post Job
    @PostMapping("/job")
    public JobResponseDTO createJob(@RequestBody JobCreateDTO dto) {
        return jobService.createJob(dto);
    }

    //update Job
    @PutMapping("/update/{jobId}")
    public JobResponseDTO updateJob(@PathVariable Long jobId, @RequestBody JobCreateDTO dto){
        return jobService.updateJob(jobId, dto);
    }

    //Delete Job
    @DeleteMapping("/delete/{jobId}")
    public void deleteJob(@PathVariable Long jobId){
        jobService.deleteJob(jobId);
    }

    //view applications
    @GetMapping("/application/{jobId}")
    public List<ApplicationResponseDTO> getAllApplication(@PathVariable Long jobId){
        return applicationService.getApplicationByJob(jobId);
    }

    //Status Update
    @PutMapping("/application/{applicationId}/status")
    public ApplicationResponseDTO updateStatus(@PathVariable Long applicationId,
                                    @RequestParam ApplicationStatus status){
        return applicationService.updateStatus(applicationId, status);
    }
}
