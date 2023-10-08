package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.entities.CandidateSkill;
import vn.edu.iuh.fit.entities.CandidateSkillID;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillID> {
}