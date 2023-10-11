package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ToString.Exclude
    private List<CandidateSkill> candidateSkills;
    @OneToMany(mappedBy = "candidate")
    @ToString.Exclude
    private List<Experience> experiences;
}
