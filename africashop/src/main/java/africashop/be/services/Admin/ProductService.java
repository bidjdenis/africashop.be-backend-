package africashop.be.services.Admin;

import africashop.be.dtos.ProductDto;

import java.io.IOException;
import java.util.List;


public interface ProductService {

    ProductDto createProduct(ProductDto productDto)throws IOException;

    List<ProductDto> getAllProduct();


}
