package vn.edu.iuh.fit.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vn.edu.iuh.fit.backend.entities.Address;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.services.CandidateServices;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class CandidateTests {
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private CandidateRepository candidateRepository;

//    @PostConstruct
    void save() {
        Address address;
        Candidate candidate;

        for (int i = 220; i <= 320; ++i) {
            address = new Address(i);
            candidate = new Candidate("Phone #" + i, LocalDate.now(), "Email #" + i, "Full name #" + i, address);

            candidateServices.save(candidate);
        }
    }

    @Test
    void findAll() {
        Assertions.assertTrue(candidateServices.findAll().size() > 100);
    }

    @Test
    void suggestCandidate() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("can_id"));
        List<Candidate> candidates = candidateRepository.suggestCandidate(10, pageRequest);

        Assertions.assertFalse(candidates.isEmpty());
    }
}