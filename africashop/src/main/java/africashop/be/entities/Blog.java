package africashop.be.entities;

import africashop.be.dtos.BlogDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public BlogDto getDto() {
        BlogDto blogDto = new BlogDto();
        blogDto.setId(id);
        blogDto.setTitle(title);
        blogDto.setContent(content);
        blogDto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        blogDto.setByteImg(img);
        return blogDto;
    }
}
