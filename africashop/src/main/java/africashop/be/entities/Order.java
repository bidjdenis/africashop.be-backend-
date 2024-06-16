package africashop.be.entities;

import africashop.be.dtos.OrderDto;
import africashop.be.dtos.OrderItemDto;
import africashop.be.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private Date date;

    private Double amount;

    private String address;

    private Long boite;

    private Long codePostale;

   private OrderStatus orderStatus;

    private Double totalAmount;
    private Double discount;

    private UUID trackingId;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems = new ArrayList<>();

    public OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(id);
        orderDto.setOrderDescription(city);
        orderDto.setAddress(address);
        orderDto.setBoite(boite);
        orderDto.setCodePostale(codePostale);
        orderDto.setTrackingId(trackingId);
        orderDto.setDate(date);
        orderDto.setAmount(amount);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setUserName(user.getName());
        orderDto.setEmail(user.getEmail());
        if(coupon != null){
            orderDto.setCouponName(coupon.getName());
        }
        List<OrderItemDto> orderItemDtos = orderItems.stream().map(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductName(orderItem.getProduct().getName());
            orderItemDto.setQuantity(orderItem.getQuantity().intValue());
            orderItemDto.setPrice(orderItem.getProduct().getPrice());
            byte[] imageBytes = orderItem.getProduct().getImg();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            orderItemDto.setProcessedImg(base64Image);
            return orderItemDto;
        }).collect(Collectors.toList());
        orderDto.setOrderItems(orderItemDtos);
        return orderDto;
    }
}
