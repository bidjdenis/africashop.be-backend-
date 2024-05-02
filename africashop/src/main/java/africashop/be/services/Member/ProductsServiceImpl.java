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
}
