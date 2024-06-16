package africashop.be.dtos;

import africashop.be.entities.OrderItems;
import africashop.be.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
public class OrderDto {

    private Long id;
    private String orderDescription;
    private Date date;
    private String address;
    private Long boite;
    private Long codePostale;
    private OrderStatus orderStatus;
    private Double amount;
    private Double totalAmount;
    private Double discount;
    private UUID trackingId;
    private String userName;
    private String email;
    private String couponName;
    private List<CartItemsDto> cartItems;
    private List<OrderItemDto> orderItems;


}
