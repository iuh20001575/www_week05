package vn.edu.iuh.fit.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.entities.Job;
import vn.edu.iuh.fit.backend.repositories.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobServices {
    private final JobRepository jobRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public JobServices(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public boolean save(Job job) {
        try {
            jobRepository.save(job);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<Job> findById(long id) {
        return jobRepository.findById(id);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Optional<Boolean> update(Job job) {
        if (!exists(job))
            return Optional.empty();

        try {
            jobRepository.save(job);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    public Optional<Boolean> delete(long id) {
        if (!exists(id))
            return Optional.empty();

        try {
            jobRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(long id) {
        return jobRepository.findById(id).isPresent();
    }

    private boolean exists(Job job) {
        return exists(job.getId());
    }

    public List<Job> findByCompanyId(long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }
}
