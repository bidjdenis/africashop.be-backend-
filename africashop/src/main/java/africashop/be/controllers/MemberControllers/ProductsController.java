package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.ProductDto;
import africashop.be.services.Member.ProductsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class ProductsController {

    private final ProductsServices productsServices;


    public ProductsController(ProductsServices productsServices) {
        this.productsServices = productsServices;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtos = productsServices.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }
}
