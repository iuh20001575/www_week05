package vn.edu.iuh.fit;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.services.CandidateServices;

import java.time.LocalDate;

@SpringBootTest
class CandidateTests {
    @Autowired
    private CandidateServices candidateServices;

    @PostConstruct
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
}