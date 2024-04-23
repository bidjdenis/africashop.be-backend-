package africashop.be.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CountryDto {

    private Long id;
    private String name;
    private byte[] byteImg;

    private MultipartFile img;
}
