package africashop.be.services.Member;

import africashop.be.dtos.BlogDto;
import africashop.be.dtos.ProductDetailDto;
import africashop.be.entities.Blog;

import java.util.List;

public interface BlogsService {
    List<BlogDto> getAllBlogs();
    Blog getBlogById(Long id);
}
