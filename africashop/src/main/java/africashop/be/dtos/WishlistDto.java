package africashop.be.dtos;

import lombok.Data;

@Data
public class WishlistDto {
    private Long userId;

    private Long productId;

    private Long id;

    private String productName;


    private byte[] returnedImg;

    private Double price;

    private String categoryName;
}
