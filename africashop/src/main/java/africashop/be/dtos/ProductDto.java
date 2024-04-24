package africashop.be.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private String weight;

    private Long categoryId;
    private String categoryName;

    private Long countryId;
    private String countryName;

    private byte[] byteImg;
    private MultipartFile img;

}
