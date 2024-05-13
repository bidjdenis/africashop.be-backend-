package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.ProductDetailDto;
import africashop.be.dtos.ProductDto;
import africashop.be.services.Member.ProductsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class ProductsController {

    private final ProductsServices productsServices;


    public ProductsController(ProductsServices productsServices) {
        this.productsServices = productsServices;
    }

    @GetMapping("/products/pagination")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(defaultValue = "0")int pageNumber){
        List<ProductDto> productDtos = productsServices.getAllProductsPagination(pageNumber);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtos = productsServices.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/countrie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCountry(@PathVariable Long id) {
        List<ProductDto> productDtos = productsServices.getProductByCountry(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Long id) {
        List<ProductDto> productDtos = productsServices.getProductByCategory(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable Long productId){
        ProductDetailDto productDetailDto = productsServices.getProductById(productId);
        if(productDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDto);
    }

    @GetMapping("/products/sort")
    public ResponseEntity<List<ProductDto>> sortByPrice(@RequestParam boolean ascending) {
        List<ProductDto> productDtos = productsServices.getProductsSortedByPrice(ascending);
        return ResponseEntity.ok(productDtos);
    }

}