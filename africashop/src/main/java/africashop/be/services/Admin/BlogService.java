package africashop.be.services.Admin;

import africashop.be.dtos.BlogDto;
import africashop.be.entities.Blog;
import africashop.be.entities.Coupon;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto )throws IOException;
    List<BlogDto> getAllBlog();



}
