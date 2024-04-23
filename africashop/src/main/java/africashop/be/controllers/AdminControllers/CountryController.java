package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.CountryDto;
import africashop.be.services.Admin.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
   public  ResponseEntity<CountryDto> createCountry(@ModelAttribute CountryDto countryDto)throws IOException {
        CountryDto countryDto1 = countryService.createCountry(countryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryDto1);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getAllCountries(){
        List<CountryDto> countryDtos = countryService.getAllCountry();
        return ResponseEntity.ok(countryDtos);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public void deleteCountry(@PathVariable Long id){
        countryService.deleteCountry(id);
    }
}
