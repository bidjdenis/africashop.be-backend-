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

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/product/update/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) throws IOException{
        ProductDto productDto1 = productService.updateProduct(id,productDto);
        if(productDto1 != null){
           return ResponseEntity.ok(productDto1);
        }else{
          return   ResponseEntity.notFound().build();
        }
    }

    @GetMapping("get/product/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDto = productService.getProductById(id);
        if(productDto != null){
            return ResponseEntity.ok(productDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
