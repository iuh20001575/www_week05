package vn.edu.iuh.fit.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.entities.Company;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServices {
    private final CompanyRepository companyRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CompanyServices(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean save(Company company) {
        try {
            companyRepository.save(company);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Boolean> update(Company company) {
        if (companyRepository.findById(company.getId()).isEmpty())
            return Optional.empty();

        try {
            companyRepository.save(company);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    public Optional<Boolean> delete(long id) {
        if (companyRepository.findById(id).isEmpty())
            return Optional.empty();

        try {
            companyRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }
}
