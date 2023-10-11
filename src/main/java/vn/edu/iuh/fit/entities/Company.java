package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    @Id
    @Column(name = "comp_id", columnDefinition = "bigint(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "varchar(255)", name = "comp_name")
    private String name;
    @Column(columnDefinition = "varchar(2000)")
    private String about;
    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "add_id")
    private Address address;
    @Column(columnDefinition = "varchar(255)")
    private String phone;
    @Column(columnDefinition = "varchar(255)", name = "web_url")
    private String webURL;
    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Job> jobs;
    @Column(columnDefinition = "varchar(255)")
    private String email;
}
