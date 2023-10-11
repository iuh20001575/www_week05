package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Column(columnDefinition = "varchar(15)")
    private String phone;
    @Id
    @Column(name = "can_id")
    private long id;
    @Column(columnDefinition = "date")
    private LocalDate dob;
    @Column(columnDefinition = "varchar(255)")
    private String email;
    @Column(columnDefinition = "varchar(255)", name = "full_name")
    private String fullName;
    @OneToOne
    @JoinColumn(referencedColumnName = "add_id", name = "address")
    private Address address;
    @OneToMany(mappedBy = "candidate")
    private List<CandidateSkill> candidateSkills;
    @OneToMany(mappedBy = "candidate")
    private List<Experience> experiences;
}
