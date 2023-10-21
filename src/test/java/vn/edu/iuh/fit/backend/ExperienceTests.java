package vn.edu.iuh.fit.backend;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.entities.Experience;
import vn.edu.iuh.fit.backend.services.ExperienceServices;

import java.time.LocalDate;

@SpringBootTest
class ExperienceTests {
    @Autowired
    private ExperienceServices experienceServices;

    @PostConstruct
    void save() {
        Candidate candidate;
        Experience experience;

        for (int i = 1; i <= 500; ++i) {
            candidate = new Candidate(i);

            for (int j = 0; j <= 5; j++) {
                experience = new Experience(LocalDate.of(2022, 3, 20), candidate, LocalDate.now(), "Company name #" + (i + j), "Role #" + (i + j), "Word description #" + (i + j));

                experienceServices.save(experience);
            }
        }
    }

    @Test
    void findAll() {
        Assertions.assertFalse(experienceServices.findAll().isEmpty());
    }
}