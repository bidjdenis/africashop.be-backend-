package africashop.be.controllers.VisitorControllers;

import africashop.be.dtos.CountryDto;
import africashop.be.dtos.ProductDto;
import africashop.be.services.Member.CountriesService;
import africashop.be.services.Member.ProductsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class HomeController {

    private final CountriesService countriesService;
    private final ProductsServices productsServices;

    public HomeController(CountriesService countriesService, ProductsServices productsServices) {
        this.countriesService = countriesService;
        this.productsServices = productsServices;
    }

    @GetMapping("/countries")
    ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countryDtos = this.countriesService.getAllCountries();
        return ResponseEntity.ok(countryDtos);
    }

    @GetMapping("/countrie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCountry(@PathVariable Long id) {
        List<ProductDto> productDtos = productsServices.getProductByCountry(id);
        return ResponseEntity.ok(productDtos);
    }
}
