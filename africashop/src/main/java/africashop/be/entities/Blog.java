package africashop.be.entities;

import africashop.be.dtos.BlogDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Date date;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public BlogDto getDto() {
        BlogDto blogDto = new BlogDto();
        blogDto.setId(id);
        blogDto.setTitle(title);
        blogDto.setContent(content);
        blogDto.setDate(date);
        blogDto.setByteImg(img);
        return blogDto;
    }
}
