package vn.edu.iuh.fit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidateSkillID implements Serializable {
    private Skill skill;
    private Candidate candidate;
}
