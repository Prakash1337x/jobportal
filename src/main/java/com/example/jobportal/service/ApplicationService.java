package com.example.jobportal.service;

import com.example.jobportal.dto.ApplicationRequestDTO;
import com.example.jobportal.dto.ApplicationResponseDTO;
import com.example.jobportal.entity.Application;
import com.example.jobportal.entity.ApplicationStatus;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.mapper.ApplicationMapper;
import com.example.jobportal.repository.ApplicationRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;
    private JobRepository jobRepository;
    private UserRepository userRepository;
    private ApplicationMapper  applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository,
                              JobRepository jobRepository,
                              UserRepository userRepository,
                              ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicationMapper = applicationMapper;
    }

    //Apply Job
    public ApplicationResponseDTO applyJob(ApplicationRequestDTO dto) {
        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(()->new RuntimeException("Job Not Found!.."));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(()->new RuntimeException("User Not Found!.."));

        Application application = applicationMapper.toEntity(dto);
        application.setJobSeeker(user);
        application.setJob(job);
        Application saved = applicationRepository.save(application);
        return applicationMapper.toDTO(saved);
    }

    //view Applications
    public List<ApplicationResponseDTO> getApplicationByJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(()->new RuntimeException("Job Not Found!.."));
        return applicationRepository.findByJob(job)
                .stream()
                .map(applicationMapper::toDTO)
                .toList();
    }

    // view applied jobs
    public List<ApplicationResponseDTO> getUserApplications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found!.."));
        return applicationRepository.findByJobSeeker(user)
                .stream()
                .map(applicationMapper::toDTO)
                .toList();
    }

    //Status Update
    public ApplicationResponseDTO updateStatus(Long applicationId, ApplicationStatus applicationStatus) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("Application Not Found!.."));
        app.setStatus(applicationStatus);
        Application saved = applicationRepository.save(app);
        return applicationMapper.toDTO(saved);
    }
}
