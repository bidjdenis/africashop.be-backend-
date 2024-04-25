package africashop.be.services.Admin;

import africashop.be.dtos.ProductDto;

import java.io.IOException;
import java.util.List;


public interface ProductService {

    ProductDto createProduct(ProductDto productDto)throws IOException;

    List<ProductDto> getAllProduct();

    void deleteProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto) throws IOException;

    ProductDto getProductById(Long id);


}
