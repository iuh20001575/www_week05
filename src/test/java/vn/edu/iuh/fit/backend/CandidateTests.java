package vn.edu.iuh.fit.backend;

import com.neovisionaries.i18n.CountryCode;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vn.edu.iuh.fit.backend.entities.Address;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.services.CandidateServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootTest
class CandidateTests {
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;

    @PostConstruct
    void save() {
        Random rnd = new Random();
        for (int i = 1; i < 1000; i++) {
            Address add = new Address("HCM", CountryCode.VN, rnd.nextInt(70000, 80000) + "", "Quang Trung",
                    rnd.nextInt(1, 1000) + "");
            addressRepository.save(add);
            Candidate can = new Candidate(
                    rnd.nextLong(1111111111L, 9999999999L) + "",
                    LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)),
                    "email_" + i + "@gmail.com", "Name #" + i
                    , add);
            candidateRepository.save(can);
            System.out.println("Added: " + can);
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

    @Test
    void getCandidatesWithPaging() {
        Page<Candidate> candidates = candidateServices.findAll(1, 10, "id", "asc");

        System.out.println(candidates);

        Assertions.assertTrue(!candidates.isEmpty());
    }
}