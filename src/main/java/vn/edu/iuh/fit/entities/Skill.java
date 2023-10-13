package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.enums.SkillType;

import java.util.List;

@Entity
@Table(name = "skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Skill {
    @Column(name = "skill_type", nullable = false)
    private SkillType type;
    @Column(name = "skill_name", length = 150, nullable = false)
    private String skillName;
    @Column(name = "skill_desc", length = 300, nullable = false)
    private String skillDescription;
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "skill")
    @ToString.Exclude
    private List<JobSkill> jobSkills;

    public Skill(long id) {
        this.id = id;
    }

    public Skill(SkillType type, String skillName, String skillDescription) {
        this.type = type;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
    }
}
