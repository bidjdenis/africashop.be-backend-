package africashop.be.dtos;

import lombok.Data;

@Data
public class CartItemsDto {

    private Long id;
    private Double price;
    private Long quantity;
    private Long productId;
    private String productName;
    private byte [] returnedImg;
    private Long userId;


}
