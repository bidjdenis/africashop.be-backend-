package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.BlogDto;
import africashop.be.dtos.CountryDto;
import africashop.be.entities.Blog;
import africashop.be.entities.Coupon;
import africashop.be.services.Admin.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/addBlog")
    public ResponseEntity<BlogDto> createBlog(@ModelAttribute BlogDto blogDto) throws IOException {
        BlogDto blogDto1 = blogService.createBlog(blogDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogDto1);
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getAllCountries() {
        List<BlogDto> blogDtos = blogService.getAllBlog();
        return ResponseEntity.ok(blogDtos);
    }
}
