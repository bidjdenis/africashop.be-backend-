package africashop.be.controllers.VisitorControllers;

import africashop.be.dtos.*;
import africashop.be.entities.Category;
import africashop.be.entities.Coupon;
import africashop.be.entities.Product;
import africashop.be.services.Member.CountriesService;
import africashop.be.services.Member.ProductsServices;
import africashop.be.services.Member.ReviewService;
import africashop.be.services.Visitor.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class HomeController {

    private final CountriesService countriesService;
    private final ProductsServices productsServices;
    private final VisitorService visitorService;
    private final ReviewService reviewService;



    @GetMapping("/countries")
    ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countryDtos = this.countriesService.getAllCountries();
        return ResponseEntity.ok(countryDtos);
    }

    @GetMapping("/countrie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCountry(@PathVariable Long id) {
        List<ProductDto> productDtos = visitorService.getProductByCountry(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/pagination")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(defaultValue = "0")int pageNumber){
        List<ProductDto> productDtos = visitorService.getAllProductsPagination(pageNumber);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtos = visitorService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Long id) {
        List<ProductDto> productDtos = visitorService.getProductByCategory(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable Long productId){
        ProductDetailDto productDetailDto = visitorService.getProductById(productId);
        if(productDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDto);
    }

    @GetMapping("/products/sort")
    public List<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "true") boolean ascending) {
        return visitorService.getProductsSortedByPrice(pageNumber, ascending);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return this.visitorService.getAllCategories();
    }

    @GetMapping("/productDetail/{id}")
    public ResponseEntity<Product> getProductDetail(@PathVariable Long id) {
        Product productDetail = productsServices.getProductDetail(id);
        return ResponseEntity.ok(productDetail);
    }

    @GetMapping("/review/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Long productId) {
        List<ReviewDto> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> coupons = visitorService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable UUID trackingId){
        OrderDto orderDto = visitorService.searchOrderByTrackingId(trackingId);
        if(orderDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDto);
    }
}
