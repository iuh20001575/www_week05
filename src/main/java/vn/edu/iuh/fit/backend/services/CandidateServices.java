package vn.edu.iuh.fit.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {
    private final CandidateRepository candidateRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CandidateServices(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public boolean save(Candidate candidate) {
        try {
            candidateRepository.save(candidate);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<Candidate> findById(long id) {
        return candidateRepository.findById(id);
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Optional<Boolean> update(Candidate candidate) {
        if (!exists(candidate))
            return Optional.empty();

        try {
            candidateRepository.save(candidate);
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
            candidateRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(long id) {
        return candidateRepository.findById(id).isPresent();
    }

    private boolean exists(Candidate candidate) {
        return exists(candidate.getId());
    }

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);

        return candidateRepository.findAll(pageRequest);
    }
}
