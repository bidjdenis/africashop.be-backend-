package africashop.be.services.Admin;

import africashop.be.dtos.CountryDto;

import java.io.IOException;
import java.util.List;

public interface CountryService {

    CountryDto createCountry(CountryDto countryDto )throws IOException;

    List<CountryDto> getAllCountry();

    void deleteCountry(Long id);

    CountryDto updateCountry(Long id, CountryDto countryDto)  throws IOException;
}
