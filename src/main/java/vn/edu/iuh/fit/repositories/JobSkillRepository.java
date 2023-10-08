package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.JobSkillID;

public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillID> {
}