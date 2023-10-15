package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.entities.JobSkill;
import vn.edu.iuh.fit.backend.ids.JobSkillID;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillID> {
    List<JobSkill> findByJobId(long jobId);
}