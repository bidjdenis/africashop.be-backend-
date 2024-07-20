package africashop.be.services.Admin;

import africashop.be.Repositories.BlogRepo;
import africashop.be.dtos.BlogDto;
import africashop.be.entities.Blog;
import africashop.be.entities.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(blogDto.getDate());
            blog.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        blog.setImg(blogDto.getImg().getBytes());
        return blogRepo.save(blog).getDto();
    }

    @Override
    public List<BlogDto> getAllBlog() {
        List<Blog> blogs = blogRepo.findAll();
        return  blogs.stream().map(Blog :: getDto).collect(Collectors.toList());
    }
}
