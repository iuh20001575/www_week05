package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.entities.CandidateSkill;
import vn.edu.iuh.fit.backend.ids.CandidateSkillID;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillID> {
}