package africashop.be.services.Member;

import africashop.be.dtos.ProductDetailDto;
import africashop.be.dtos.ProductDto;
import africashop.be.entities.Product;

import java.util.List;

public interface ProductsServices {

    List<ProductDto> getAllProducts();
    List<ProductDto> getProductByCountry(Long id);
    List<ProductDto> getProductByCategory(Long id);
    ProductDto converToDto(Product product);
    ProductDetailDto getProductById(Long productId);
    List<ProductDto> getProductsSortedByPrice(boolean ascending);

}
