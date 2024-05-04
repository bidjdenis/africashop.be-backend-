package africashop.be.services.Member;

import africashop.be.Repositories.ProductRepo;
import africashop.be.dtos.ProductDto;
import africashop.be.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsServices{

    private final ProductRepo productRepo;

    public ProductsServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = this.productRepo.findAll();
        return productList.stream().map(Product :: getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByCountry(Long id) {
        List<Product>  products = productRepo.findByCountryId(id);
        return products.stream().map(Product :: getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByCategory(Long id) {
        List<Product> products = productRepo.findByCategoryId(id);
        return products.stream().map(this::converToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto converToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setWeight(product.getWeight());
        productDto.setByteImg(product.getImg());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setCountryName(product.getCountry().getName());
        return productDto;
    }
}
