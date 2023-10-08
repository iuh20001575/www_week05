package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Job {
    @Id
    @Column(name = "job_id", nullable = false)
    private long id;
    @Column(name = "job_name", unique = true)
    private String name;
    @ManyToOne
    @JoinColumn
    private Company company;
    @OneToMany(mappedBy = "job")
    private List<JobSkill> jobSkills;
    @Column(name = "job_desc", length = 2000)
    private String description;

}
