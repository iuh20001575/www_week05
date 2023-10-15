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

        for (int i = 4; i <= 101; ++i) {
            candidate = new Candidate(i);
            experience = new Experience(LocalDate.of(2022, 3, 20), candidate, LocalDate.now(), "Company name #" + i, "Role #" + i, "Word description #" + i);

            experienceServices.save(experience);
        }
    }

    @Test
    void findAll() {
        Assertions.assertFalse(experienceServices.findAll().isEmpty());
    }
}