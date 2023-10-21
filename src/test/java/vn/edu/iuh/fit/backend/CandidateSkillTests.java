package vn.edu.iuh.fit.backend;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.backend.entities.Candidate;
import vn.edu.iuh.fit.backend.entities.CandidateSkill;
import vn.edu.iuh.fit.backend.entities.Skill;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.services.CandidateSkillServices;

import java.util.List;

@SpringBootTest
public class CandidateSkillTests {
    private final CandidateSkillServices candidateSkillServices;

    @Autowired
    public CandidateSkillTests(CandidateSkillServices candidateSkillServices) {
        this.candidateSkillServices = candidateSkillServices;
    }

    @PostConstruct
    void save() {
        Candidate candidate;
        Skill skill;
        CandidateSkill candidateSkill;
        SkillLevel[] skillLevels = SkillLevel.values();
        int skillLevelsSize = skillLevels.length;

        for (int i = 1; i <= 500; ++i) {
            candidate = new Candidate(i);

            for (int j = 0; j <= 5; j++) {
                skill = new Skill(i + j);
                candidateSkill = new CandidateSkill(skillLevels[(int) (Math.random() * skillLevelsSize)], skill, candidate, "More info #" + (i+j));
                candidateSkillServices.save(candidateSkill);
            }

        }
    }

    @Test
    void findAll() {
        List<CandidateSkill> candidateSkills = candidateSkillServices.findAll();

        Assertions.assertFalse(candidateSkills.isEmpty());
    }
}
