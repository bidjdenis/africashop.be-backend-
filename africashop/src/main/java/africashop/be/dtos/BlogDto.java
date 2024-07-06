package africashop.be.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class BlogDto {

    private Long id;

    private String title;

    private String content;

    private Date date;

    private byte[] byteImg;

    private MultipartFile img;
}
