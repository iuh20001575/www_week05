package vn.edu.iuh.fit.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.entities.CandidateSkill;
import vn.edu.iuh.fit.entities.CandidateSkillID;
import vn.edu.iuh.fit.repositories.CandidateSkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillServices {
    private final CandidateSkillRepository candidateSkillRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CandidateSkillServices(CandidateSkillRepository candidateSkillRepository) {
        this.candidateSkillRepository = candidateSkillRepository;
    }

    public boolean save(CandidateSkill candidateSkill) {
        try {
            candidateSkillRepository.save(candidateSkill);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<CandidateSkill> findById(CandidateSkillID candidateSkillID) {
        return candidateSkillRepository.findById(candidateSkillID);
    }

    public List<CandidateSkill> findAll() {
        return candidateSkillRepository.findAll();
    }

    public Optional<Boolean> update(CandidateSkill candidateSkill) {
        if (!exists(candidateSkill))
            return Optional.empty();

        try {
            candidateSkillRepository.save(candidateSkill);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    public Optional<Boolean> delete(CandidateSkillID candidateSkillID) {
        if (!exists(candidateSkillID))
            return Optional.empty();

        try {
            candidateSkillRepository.deleteById(candidateSkillID);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(CandidateSkillID candidateSkillID) {
        return candidateSkillRepository.findById(candidateSkillID).isPresent();
    }

    private boolean exists(CandidateSkill candidateSkill) {
        return exists(getID(candidateSkill));
    }

    private CandidateSkillID getID(CandidateSkill candidateSkill) {
        return new CandidateSkillID(candidateSkill.getSkill(), candidateSkill.getCandidate());
    }
}
