package vn.edu.iuh.fit.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.JobSkillID;
import vn.edu.iuh.fit.repositories.JobSkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobSkillServices {
    private final JobSkillRepository jobSkillRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public JobSkillServices(JobSkillRepository jobSkillRepository) {
        this.jobSkillRepository = jobSkillRepository;
    }

    public boolean save(JobSkill jobSkill) {
        try {
            jobSkillRepository.save(jobSkill);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<JobSkill> findById(JobSkillID jobSkillID) {
        return jobSkillRepository.findById(jobSkillID);
    }

    public List<JobSkill> findAll() {
        return jobSkillRepository.findAll();
    }

    public Optional<Boolean> update(JobSkill jobSkill) {
        if (!exists(jobSkill)) return Optional.empty();

        try {
            jobSkillRepository.save(jobSkill);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    public Optional<Boolean> delete(JobSkillID jobSkillID) {
        if (!exists(jobSkillID)) return Optional.empty();

        try {
            jobSkillRepository.deleteById(jobSkillID);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(JobSkillID jobSkillID) {
        return jobSkillRepository.findById(jobSkillID).isPresent();
    }

    private boolean exists(JobSkill jobSkill) {
        return exists(getID(jobSkill));
    }

    private JobSkillID getID(JobSkill jobSkill) {
        return new JobSkillID(jobSkill.getJob(), jobSkill.getSkill());
    }
}
