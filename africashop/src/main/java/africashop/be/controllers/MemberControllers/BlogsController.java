package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.BlogDto;
import africashop.be.dtos.ProductDetailDto;
import africashop.be.dtos.ProductDto;
import africashop.be.services.Member.BlogsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class BlogsController {

    private final BlogsService blogsService;

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getBlogs(){
        List<BlogDto> blogDtos = blogsService.getAllBlogs();
        return ResponseEntity.ok(blogDtos);
    }

    @GetMapping("/blog/{blogId}")
    public ResponseEntity<BlogDto> getBlogId(@PathVariable Long blogId){
        BlogDto blogDto = blogsService.getBlogById(blogId).getDto();
        if(blogDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(blogDto);
    }

}
