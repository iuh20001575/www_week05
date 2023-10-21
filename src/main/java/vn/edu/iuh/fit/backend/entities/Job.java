package vn.edu.iuh.fit.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Job implements Serializable {
    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "job_name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
    @OneToMany(mappedBy = "job", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<JobSkill> jobSkills;
    @Column(name = "job_desc", length = 2000, nullable = false)
    private String description;

    public Job(long id) {
        this.id = id;
    }

    public Job(String name, Company company, String description) {
        this.name = name;
        this.company = company;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", description='" + description + '\'' +
                '}';
    }
}
