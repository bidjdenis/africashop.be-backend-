package africashop.be.services.Admin;

import africashop.be.dtos.ProductDto;

import java.io.IOException;


public interface ProductService {

    ProductDto createProduct(ProductDto productDto)throws IOException;


}
