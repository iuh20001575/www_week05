package vn.edu.iuh.fit.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.entities.Candidate;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query(value = "SELECT * FROM candidate c WHERE c.can_id IN (SELECT distinct cs.can_id FROM job_skill js JOIN candidate_skill cs ON js.skill_id = cs.skill_id WHERE js.job_id IN (SELECT distinct j.job_id FROM job j WHERE j.company = :companyId) AND cs.skill_level = js.skill_level)", nativeQuery = true)
    List<Candidate> suggestCandidate(long companyId, Pageable pageable);
}