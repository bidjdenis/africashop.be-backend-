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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product :: getDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(id);
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        Optional<Country> optionalCountry = countryRepo.findById(productDto.getCountryId());

        if(optionalProduct.isPresent() && optionalCategory.isPresent() && optionalCountry.isPresent()){
            Product product = optionalProduct.get();

            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setWeight(productDto.getWeight());
            product.setDescription(productDto.getDescription());
            product.setCategory(optionalCategory.get());
            product.setCountry(optionalCountry.get());
            if(productDto.getImg() != null){
                product.setImg(productDto.getImg().getBytes());
            }
            return productRepo.save(product).getDto();

        }else{
            return null;
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get().getDto();
        }
        else {
            return null;
        }
    }


}
