package africashop.be.services.Visitor;

import africashop.be.dtos.ProductDetailDto;
import africashop.be.dtos.ProductDto;
import africashop.be.entities.Category;
import africashop.be.entities.Product;

import java.util.List;

public interface VisitorService {

    public List<ProductDto> getAllProducts();
    List<ProductDto> getAllProductsPagination(int pageNumber);
    List<ProductDto> getProductByCountry(Long id);
    List<ProductDto> getProductByCategory(Long id);
    ProductDto converToDto(Product product);
    ProductDetailDto getProductById(Long productId);

    List<ProductDto> getProductsSortedByPrice(int pageNumber, boolean ascending);
    List<Category> getAllCategories();


}
