package com.example.jobportal.service;

import com.example.jobportal.dto.JobCreateDTO;
import com.example.jobportal.dto.JobResponseDTO;
import com.example.jobportal.entity.Category;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.JobMapper;
import com.example.jobportal.repository.CategoryRepository;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    public JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyRepository  companyRepository;
    private final CategoryRepository categoryRepository;

    public JobService(JobRepository jobRepository, JobMapper jobMapper,
                      CompanyRepository companyRepository, CategoryRepository categoryRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
    }


    //ByTitle
    public List<JobResponseDTO> searchByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    //ByLocation
    public List<JobResponseDTO> searchByLocation(String location) {
        return jobRepository.findByLocationIgnoreCase(location)
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    //BySalary
    public List<JobResponseDTO> searchBySalary(double salary) {
        return jobRepository.findBySalaryGreaterThanEqual(salary)
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    //ByExperience
    public List<JobResponseDTO> searchByExperience(int experience) {
        return jobRepository.findByExperienceLessThanEqual(experience)
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    //ByJobType
    public List<JobResponseDTO> saerchByJobType(String jobType) {
        return jobRepository.findByJobTypeIgnoreCase(jobType)
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    //create job
    public JobResponseDTO createJob(JobCreateDTO dto) {

        Job job = jobMapper.toEntity(dto);
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(()-> new ResourceNotFoundException("Company Not Found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Category not Found"));
        job.setCompany(company);
        job.setCategory(category);
        Job saved = jobRepository.save(job);
        return jobMapper.toDTO(saved);
    }

    //Update job
    public JobResponseDTO updateJob(Long jobId, JobCreateDTO dto){
        Job existing = jobRepository.findById(jobId)
                .orElseThrow(()->new ResourceNotFoundException("Job not found"));
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(()->new ResourceNotFoundException("Company Not Found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Category not Found"));

        jobMapper.updateJob(existing, dto);
        existing.setCompany(company);
        existing.setCategory(category);

        Job saved = jobRepository.save(existing);
        return jobMapper.toDTO(saved);
    }

    //GetAll
    public List<JobResponseDTO> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    //delete job
    public void deleteJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(()->new RuntimeException("job not found"));
        jobRepository.delete(job);
    }
}
