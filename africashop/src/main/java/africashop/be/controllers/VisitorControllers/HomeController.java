package africashop.be.controllers.VisitorControllers;

import africashop.be.dtos.CountryDto;
import africashop.be.dtos.ProductDetailDto;
import africashop.be.dtos.ProductDto;
import africashop.be.entities.Category;
import africashop.be.services.Member.CountriesService;
import africashop.be.services.Member.ProductsServices;
import africashop.be.services.Visitor.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class HomeController {

    private final CountriesService countriesService;
    private final ProductsServices productsServices;
    private final VisitorService visitorService;



    @GetMapping("/countries")
    ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countryDtos = this.countriesService.getAllCountries();
        return ResponseEntity.ok(countryDtos);
    }

    @GetMapping("/countrie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCountry(@PathVariable Long id) {
        List<ProductDto> productDtos = visitorService.getProductByCountry(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/pagination")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(defaultValue = "0")int pageNumber){
        List<ProductDto> productDtos = visitorService.getAllProductsPagination(pageNumber);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtos = visitorService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Long id) {
        List<ProductDto> productDtos = visitorService.getProductByCategory(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable Long productId){
        ProductDetailDto productDetailDto = visitorService.getProductById(productId);
        if(productDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDto);
    }

    @GetMapping("/products/sort")
    public List<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "true") boolean ascending) {
        return visitorService.getProductsSortedByPrice(pageNumber, ascending);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return this.visitorService.getAllCategories();
    }
}
