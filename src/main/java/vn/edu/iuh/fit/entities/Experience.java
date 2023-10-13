package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "experience")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Experience {
    @Id
    @Column(name = "exp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Temporal(TemporalType.DATE)
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;
    @Column(length = 120, name = "company", nullable = false)
    private String companyName;
    @Column(length = 100, nullable = false)
    private String role;
    @Column(length = 400, name = "work_desc", nullable = false)
    private String workDescription;

    public Experience(LocalDate toDate, Candidate candidate, LocalDate fromDate, String companyName, String role, String workDescription) {
        this.toDate = toDate;
        this.candidate = candidate;
        this.fromDate = fromDate;
        this.companyName = companyName;
        this.role = role;
        this.workDescription = workDescription;
    }
}
