package vn.edu.iuh.fit;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entities.Company;
import vn.edu.iuh.fit.entities.Job;
import vn.edu.iuh.fit.services.JobServices;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class JobTests {
    private final JobServices jobServices;

    @Autowired
    public JobTests(JobServices jobServices) {
        this.jobServices = jobServices;
    }

    @PostConstruct
    void save() {
        Job job;
        Company company;
        for (int i = 1; i <= 100; ++i) {
            company = new Company(i);

            for (int j = 1; j <= 5; ++j) {
                job = new Job("Name #" + (i + j), company, "Description #" + (i + j));
                jobServices.save(job);
            }

        }
    }

    @Test
    void findSuccessById() {
        Optional<Job> jobOptional = jobServices.findById(50);

        Assertions.assertTrue(jobOptional.isPresent());
    }

    @Test
    void findFailById() {
        Optional<Job> jobOptional = jobServices.findById(9999);

        Assertions.assertTrue(jobOptional.isEmpty());
    }

    @Test
    void findAll() {
        List<Job> jobs = jobServices.findAll();

        Assertions.assertTrue(jobs.size() > 50);
    }

    @Test
    void update() {
        Optional<Job> jobOptional = jobServices.findById(1);

        if (jobOptional.isEmpty())
            Assertions.fail();

        Job job = jobOptional.get();
        String newName = "This is new name";

        job.setName(newName);

        Optional<Boolean> updated = jobServices.update(job);

        if (updated.isEmpty())
            Assertions.fail();

        Optional<Job> jobUpdatedOptional = jobServices.findById(1);

        Assertions.assertTrue(jobUpdatedOptional.isPresent() && jobUpdatedOptional.get().getName().equals(newName));
    }

    @Test
    void deleteSuccess() {
        Optional<Boolean> optional = jobServices.delete(500);

        Assertions.assertTrue(optional.isPresent() && optional.get());
    }

    @Test
    void deleteFail() {
        Optional<Boolean> optional = jobServices.delete(999);

        Assertions.assertTrue(optional.isEmpty() || !optional.get());
    }
}