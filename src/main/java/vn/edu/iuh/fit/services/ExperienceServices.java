package vn.edu.iuh.fit.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.entities.Experience;
import vn.edu.iuh.fit.repositories.ExperienceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServices {
    private final ExperienceRepository experienceRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ExperienceServices(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public boolean save(Experience experience) {
        try {
            experienceRepository.save(experience);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<Experience> findById(long id) {
        return experienceRepository.findById(id);
    }

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    public Optional<Boolean> update(Experience experience) {
        if (!exists(experience))
            return Optional.empty();

        try {
            experienceRepository.save(experience);
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
            experienceRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(long id) {
        return experienceRepository.findById(id).isPresent();
    }

    private boolean exists(Experience experience) {
        return exists(experience.getId());
    }
}
