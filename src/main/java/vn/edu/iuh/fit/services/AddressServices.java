package vn.edu.iuh.fit.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServices {
    private final AddressRepository addressRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public AddressServices(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public boolean save(Address address) {
        try {
            addressRepository.save(address);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Boolean> update(Address address) {
        if (!exists(address))
            return Optional.empty();

        try {
            addressRepository.save(address);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    public Optional<Boolean> delete(long id) {
        if (!exists(id))
            return Optional.empty();

        try {
            addressRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.of(false);
    }

    private boolean exists(long id) {
        return addressRepository.existsById(id);
    }

    private boolean exists(Address address) {
        return exists(address.getId());
    }
}
