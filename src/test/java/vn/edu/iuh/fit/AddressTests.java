package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.services.AddressServices;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressTests {
    @Autowired
    private AddressServices addressServices;

//    @Test
//    void save() {
//        Address address = new Address("Hồ Chí Minh", CountryCode.AC, "70000", "178/11", "123");
//
//        addressServices.save(address);
//
//        Optional<Address> addressOptional = addressServices.findById(222);
//
//        Assertions.assertTrue(addressOptional.isPresent());
//    }

    @Test
    void findSuccessById() {
        Assertions.assertTrue(addressServices.findById(100).isPresent());
    }

    @Test
    void findFailById() {
        Assertions.assertTrue(addressServices.findById(999).isEmpty());
    }

    @Test
    void findAll() {
        Assertions.assertTrue(addressServices.findAll().size() > 100);
    }

    @Test
    void update() {
        Optional<Address> addressOptional = addressServices.findById(1);

        if (addressOptional.isEmpty())
            Assertions.fail();

        String newStreet = "123 Nguyễn Văn Bảo";
        Address address = addressOptional.get();
        address.setStreet(newStreet);

        addressServices.save(address);

        Optional<Address> addressUpdatedOptional = addressServices.findById(1);

        Assertions.assertTrue(addressUpdatedOptional.isPresent() && addressUpdatedOptional.get().getStreet().equals(newStreet));
    }

    @Test
    void deleteSuccess() {
        Optional<Boolean> optional = addressServices.delete(222);

        Assertions.assertTrue(optional.isPresent() && optional.get());
    }

    @Test
    void deleteFail() {
        Optional<Boolean> optional = addressServices.delete(999);

        Assertions.assertTrue(optional.isEmpty() || !optional.get());
    }
}