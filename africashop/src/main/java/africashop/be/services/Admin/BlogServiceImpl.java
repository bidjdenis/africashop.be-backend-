package africashop.be.services.Admin;

import africashop.be.Repositories.BlogRepo;
import africashop.be.dtos.BlogDto;
import africashop.be.entities.Blog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogRepo blogRepo;


    @Override
    public BlogDto createBlog(BlogDto blogDto) throws IOException {
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setDate(blogDto.getDate());
        blog.setImg(blogDto.getImg().getBytes());
        return blogRepo.save(blog).getDto();
    }
}
