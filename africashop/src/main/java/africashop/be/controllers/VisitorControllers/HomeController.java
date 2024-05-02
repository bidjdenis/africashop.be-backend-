package africashop.be.controllers.VisitorControllers;

import africashop.be.dtos.CountryDto;
import africashop.be.services.Member.CountriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class HomeController {

    private final CountriesService countriesService;

    public HomeController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping("/countries")
    ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countryDtos = this.countriesService.getAllCountries();
        return ResponseEntity.ok(countryDtos);
    }

}
