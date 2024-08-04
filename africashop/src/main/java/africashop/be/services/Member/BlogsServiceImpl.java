package africashop.be.services.Member;

import africashop.be.Repositories.BlogRepo;
import africashop.be.dtos.BlogDto;
import africashop.be.entities.Blog;
import africashop.be.entities.Product;
import africashop.be.services.Member.BlogsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlogsServiceImpl implements BlogsService {

    private final BlogRepo blogRepo;


    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepo.findAll();
        return blogs.stream().map(Blog::getDto).collect(Collectors.toList());
    }
}
