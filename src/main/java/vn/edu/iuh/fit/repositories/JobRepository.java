package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.entities.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyId(long company);

    @Query("FROM Job j WHERE j.id IN (SELECT js.job.id FROM CandidateSkill sk JOIN sk.skill s JOIN s.jobSkills js WHERE sk.candidate.id = :candidateId and js.skillLevel = sk.skillLevel)")
    List<Job> suggestJobsByCandidate(long candidateId);
}