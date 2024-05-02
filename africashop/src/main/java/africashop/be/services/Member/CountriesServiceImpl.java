package africashop.be.services.Member;

import africashop.be.Repositories.CountryRepo;
import africashop.be.dtos.CountryDto;
import africashop.be.entities.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements CountriesService{

    private final CountryRepo countryRepo;

    public CountriesServiceImpl(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }


    @Override
    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepo.findAll();
        return countries.stream().map(Country::getDto).collect(Collectors.toList());
    }
}
