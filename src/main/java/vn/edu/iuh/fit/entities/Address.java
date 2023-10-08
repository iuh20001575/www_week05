package vn.edu.iuh.fit.entities;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Column(columnDefinition = "varchar(50)")
    private String city;
    private CountryCode country;
    @Id
    @Column(name = "add_id")
    private long id;
    @Column(columnDefinition = "varchar(7)")
    private String zipcode;
    @Column(columnDefinition = "varchar(150)", name = "street")
    private String Street;
    @OneToOne(mappedBy = "address")
    private Candidate candidate;
    @Column(columnDefinition = "varchar(20)")
    private String number;
    @OneToOne(mappedBy = "address")
    private Company company;
}
