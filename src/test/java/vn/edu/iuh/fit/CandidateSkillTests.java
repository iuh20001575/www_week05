package vn.edu.iuh.fit;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.CandidateSkill;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.services.CandidateSkillServices;

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

        for (int i = 4; i <= 101; ++i) {
            candidate = new Candidate(i);
            skill = new Skill(i);
            candidateSkill = new CandidateSkill(skillLevels[(int) (Math.random() * skillLevelsSize)], skill, candidate, "More info #" + i);

            candidateSkillServices.save(candidateSkill);
        }
    }

    @Test
    void findAll() {
        List<CandidateSkill> candidateSkills = candidateSkillServices.findAll();

        Assertions.assertFalse(candidateSkills.isEmpty());
    }
}
