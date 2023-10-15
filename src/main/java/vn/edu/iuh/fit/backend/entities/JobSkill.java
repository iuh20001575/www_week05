package vn.edu.iuh.fit.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.JobSkillID;

@Entity
@Table(name = "job_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(JobSkillID.class)
public class JobSkill {
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(name = "more_infos", length = 1000)
    private String moreInfo;
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
}
