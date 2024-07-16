package africashop.be.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BlogDto {

    private Long id;

    private String title;

    private String content;

    private String date;

    private byte[] byteImg;

    private MultipartFile img;

    public Date getDateAsDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    
    }

}
