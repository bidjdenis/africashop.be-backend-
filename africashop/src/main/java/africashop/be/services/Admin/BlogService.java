package africashop.be.services.Admin;

import africashop.be.dtos.BlogDto;
import africashop.be.dtos.CountryDto;

import java.io.IOException;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto )throws IOException;

}
