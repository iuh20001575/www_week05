package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}