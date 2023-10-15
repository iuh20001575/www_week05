package vn.edu.iuh.fit.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServices {
    private final SkillRepository skillRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public SkillServices(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public boolean save(Skill skill) {
        try {
            skillRepository.save(skill);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<Skill> findById(long id) {
        return skillRepository.findById(id);
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Optional<Boolean> update(Skill skill) {
        if (!exists(skill))
            return Optional.empty();

        try {
            skillRepository.save(skill);
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
            skillRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(long id) {
        return skillRepository.findById(id).isPresent();
    }

    private boolean exists(Skill skill) {
        return exists(skill.getId());
    }
}
