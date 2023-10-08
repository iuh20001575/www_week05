package vn.edu.iuh.fit.config;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public class CountryCodeConverter implements AttributeConverter<CountryCode, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CountryCode attribute) {
        return attribute.getNumeric();
    }

    @Override
    public CountryCode convertToEntityAttribute(Integer dbData) {
        return Arrays.stream(CountryCode.values())
                .filter(code -> code.getNumeric() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
