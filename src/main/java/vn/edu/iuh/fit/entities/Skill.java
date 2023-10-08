package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.enums.SkillType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Skill {
    @Column(name = "skill_type")
    private SkillType type;
    @Column(name = "skill_name",length = 150)
    private String skillName;
    @Column(name = "skill_desc",length = 300)
    private String skillDescription;
    @Id
    @Column(name = "skill_id")
    private long id;
    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills;
}
