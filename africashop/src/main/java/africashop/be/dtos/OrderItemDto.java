package africashop.be.dtos;

import lombok.Data;

@Data
public class OrderItemDto {
    private String productName;
    private String processedImg;
    private int quantity;
    private double price;
}
