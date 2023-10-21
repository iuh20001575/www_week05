package vn.edu.iuh.fit.backend;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.services.SkillServices;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SkillTests {
    private final SkillServices skillServices;
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    public SkillTests(SkillServices skillServices) {
        this.skillServices = skillServices;
    }

    @PostConstruct
    void save() {
        Skill skill;
        for (int i = 1; i <= 1000; ++i) {
            skill = new Skill(SkillType.values()[(int) (Math.random() * 3)], "Skill name #" + i, "Skill description #" + i);

            skillServices.save(skill);
        }
    }

    @Test
    void findSuccessById() {
        Optional<Skill> jobOptional = skillServices.findById(50);

        Assertions.assertTrue(jobOptional.isPresent());
    }

    @Test
    void findFailById() {
        Optional<Skill> jobOptional = skillServices.findById(9999);

        Assertions.assertTrue(jobOptional.isEmpty());
    }

    @Test
    void findAll() {
        List<Skill> skills = skillServices.findAll();

        Assertions.assertTrue(skills.size() > 50);
    }

    @Test
    void update() {
        Optional<Skill> skillOptional = skillServices.findById(1);

        if (skillOptional.isEmpty())
            Assertions.fail();

        Skill skill = skillOptional.get();
        String newName = "This is new name";

        skill.setSkillName(newName);

        Optional<Boolean> updated = skillServices.update(skill);

        if (updated.isEmpty())
            Assertions.fail();

        Optional<Skill> skillUpdatedOptional = skillServices.findById(1);

        Assertions.assertTrue(skillUpdatedOptional.isPresent() && skillUpdatedOptional.get().getSkillName().equals(newName));
    }

    @Test
    void deleteSuccess() {
        Optional<Boolean> optional = skillServices.delete(101);

        Assertions.assertTrue(optional.isPresent() && optional.get());
    }

    @Test
    void deleteFail() {
        Optional<Boolean> optional = skillServices.delete(999);

        Assertions.assertTrue(optional.isEmpty() || !optional.get());
    }

    @Test
    void suggestForCandidate() {
        PageRequest pageRequest = PageRequest.of(3, 10);
        List<Skill> skills = skillRepository.suggestForCandidate(10, pageRequest);

        skills.forEach(System.out::println);

        Assertions.assertFalse(skills::isEmpty);
    }
}