package africashop.be.services.Admin;

import africashop.be.Repositories.CategoryRepo;
import africashop.be.Repositories.CountryRepo;
import africashop.be.Repositories.ProductRepo;
import africashop.be.dtos.ProductDto;
import africashop.be.entities.Category;
import africashop.be.entities.Country;
import africashop.be.entities.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final CountryRepo countryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, CountryRepo countryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.countryRepo = countryRepo;
    }


    @Override
    public ProductDto createProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setDescription(productDto.getDescription());
        product.setImg(productDto.getImg().getBytes());

        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
        Country country = countryRepo.findById(productDto.getCountryId()).orElseThrow();
        product.setCategory(category);
        product.setCountry(country);
        return productRepo.save(product).getDto();
    }
}
