package vn.edu.iuh.fit;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entities.Job;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.JobSkillID;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.services.JobSkillServices;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JobSkillTests {
    private final JobSkillServices jobSkillServices;

    @Autowired
    public JobSkillTests(JobSkillServices jobSkillServices) {
        this.jobSkillServices = jobSkillServices;
    }

    @PostConstruct
    void save() {
        Job job;
        Skill skill;
        JobSkill jobSkill;
        SkillLevel[] skillLevels = SkillLevel.values();
        int skillLevelsSize = skillLevels.length;

        for (int i = 1; i <= 99; ++i) {
            job = new Job(i * 5);
            skill = new Skill(i);
            jobSkill = new JobSkill(skillLevels[(int) (Math.random() * skillLevelsSize)], job, "More info #" + i, skill);

            jobSkillServices.save(jobSkill);
        }
    }

    @Test
    void findSuccessById() {
        JobSkillID jobSkillID = new JobSkillID(250, 50);
        Optional<JobSkill> jobOptional = jobSkillServices.findById(jobSkillID);

        Assertions.assertTrue(jobOptional.isPresent());
    }

    @Test
    void findFailById() {
        JobSkillID jobSkillID = new JobSkillID(999, 999);
        Optional<JobSkill> jobOptional = jobSkillServices.findById(jobSkillID);

        Assertions.assertTrue(jobOptional.isEmpty());
    }

    @Test
    void findAll() {
        List<JobSkill> jobSkills = jobSkillServices.findAll();

        Assertions.assertFalse(jobSkills.isEmpty());
    }

    @Test
    void update() {
        JobSkillID jobSkillID = new JobSkillID(5, 1);
        Optional<JobSkill> jobSkillOptional = jobSkillServices.findById(jobSkillID);

        if (jobSkillOptional.isEmpty())
            Assertions.fail();

        JobSkill jobSkill = jobSkillOptional.get();
        String newMoreInfo = "This is new more info";

        jobSkill.setMoreInfo(newMoreInfo);

        Optional<Boolean> updated = jobSkillServices.update(jobSkill);

        if (updated.isEmpty())
            Assertions.fail();

        Optional<JobSkill> jobSkillUpdatedOptional = jobSkillServices.findById(jobSkillID);

        Assertions.assertTrue(jobSkillUpdatedOptional.isPresent() &&
                jobSkillUpdatedOptional.get().getMoreInfo().equals(newMoreInfo));
    }

    @Test
    void deleteSuccess() {
        JobSkillID jobSkillID = new JobSkillID(275, 55);
        Optional<Boolean> optional = jobSkillServices.delete(jobSkillID);

        Assertions.assertTrue(optional.isPresent() && optional.get());
    }

    @Test
    void deleteFail() {
        JobSkillID jobSkillID = new JobSkillID(500, 100);
        Optional<Boolean> optional = jobSkillServices.delete(jobSkillID);

        Assertions.assertTrue(optional.isEmpty() || !optional.get());
    }
}
