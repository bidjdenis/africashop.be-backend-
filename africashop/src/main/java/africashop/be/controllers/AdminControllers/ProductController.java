package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.ProductDto;
import africashop.be.entities.Product;
import africashop.be.services.Admin.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public  ResponseEntity<ProductDto>  createProduct(@ModelAttribute ProductDto productDto) throws IOException{
        ProductDto productDto1 = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductDto>> getAllProduct(){
        List<ProductDto> productList = productService.getAllProduct();
        return ResponseEntity.ok(productList);
    }
}
