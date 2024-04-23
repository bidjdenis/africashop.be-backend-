package africashop.be.services.Admin;

import africashop.be.Repositories.CountryRepo;
import africashop.be.dtos.CountryDto;
import africashop.be.entities.Country;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryRepo countryRepo;

    public CountryServiceImpl(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) throws IOException {
        Country country = new Country();
        country.setId(countryDto.getId());
        country.setName(countryDto.getName());
        country.setImg(countryDto.getImg().getBytes());

        return countryRepo.save(country).getDto();
    }

    @Override
    public List<CountryDto> getAllCountry() {
        List<Country> countries = countryRepo.findAll();
        return  countries.stream().map(Country :: getDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepo.deleteById(id);
    }
}
