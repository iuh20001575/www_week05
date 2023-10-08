package vn.edu.iuh.fit;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PostLoad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;

import java.time.LocalDate;

@SpringBootTest
class WwwWeek03ApplicationTests {
	@Autowired
	CandidateRepository candidateRepository;


	@PostConstruct
	void contextLoads() {

		for (int i = 0; i < 10; i++) {
//			Candidate candidate = new Candidate("0122", 1L, LocalDate.now(), "email", "Nam", new Address());
			candidateRepository.save(new Candidate());
		}
	}

}
