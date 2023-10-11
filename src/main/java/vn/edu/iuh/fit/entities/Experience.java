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
    private long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "to_date")
    private LocalDate toDate;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Temporal(TemporalType.DATE)
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(length = 120, name = "company")
    private String companyName;
    @Column(length = 100)
    private String role;
    @Column(length = 400, name = "work_desc")
    private String workDescription;
}
