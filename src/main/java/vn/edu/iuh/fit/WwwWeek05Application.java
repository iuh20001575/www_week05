package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.repositories.AddressRepository;

import java.util.List;


@SpringBootApplication
public class WwwWeek05Application {
    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(WwwWeek05Application.class, args);
    }

    @Bean
    CommandLineRunner test() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Address address = new Address("Hồ Chí Minh", CountryCode.AD, "70000", "Nguyễn Văn Bảo", "14");

                addressRepository.save(address);

                List<Address> all = addressRepository.findAll();

                all.forEach(System.out::println);
            }
        };
    }
}
