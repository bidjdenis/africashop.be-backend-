package africashop.be.services.Member;

import africashop.be.Repositories.*;
import africashop.be.dtos.CartItemsDto;
import africashop.be.dtos.OrderDto;
import africashop.be.entities.*;
import africashop.be.enums.OrderStatus;
import africashop.be.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartItemsRepo cartItemsRepo;

    private final OrderRepo orderRepo;

    private final CartService cartService;

    private final CouponRepo couponRepo;

    @Override
    public List<CartItemsDto> validationOrder(Long userId) {
            if (orderRepo.existsByUserIdAndOrderStatus(userId, OrderStatus.Pending)) {
                throw new ValidationException("Une commande existe déjà pour cet utilisateur.");
            }

            Order order = new Order();
            order.setOrderStatus(OrderStatus.Pending);
            order.setTotalAmount(0.0);
            order.setAmount(0.0);

            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                order.setUser(user);
            } else {
                throw new ValidationException("Utilisateur non trouvé.");
            }

            List<CartItems> cartItems = cartItemsRepo.findByUserId(userId);
            List<CartItemsDto> cartItemsDtoList = new ArrayList<>();

            for (CartItems c : cartItems) {
                CartItemsDto cartItemsDto = new CartItemsDto();
                cartItemsDto.setId(c.getId());
                cartItemsDto.setUserId(userId);
                cartItemsDto.setProductId(c.getProduct().getId());
                cartItemsDto.setProductName(c.getProduct().getName());
                cartItemsDto.setPrice(c.getPrice());
                cartItemsDto.setQuantity(c.getQuantity());
                cartItemsDto.setReturnedImg(c.getProduct().getImg());
                cartItemsDtoList.add(cartItemsDto);

                order.setTotalAmount(order.getTotalAmount() + (c.getPrice() * c.getQuantity()));
                order.setAmount(order.getAmount() + (c.getPrice() * c.getQuantity()));
                order.setDate(new Date());
                order.setTrackingId(UUID.randomUUID());

                OrderItems orderItems = new OrderItems();
                orderItems.setOrder(order);
                orderItems.setProduct(c.getProduct());
                orderItems.setQuantity(c.getQuantity());

                order.getOrderItems().add(orderItems);
            }

            orderRepo.save(order);

            return cartItemsDtoList;
        }

    @Override
    public OrderDto getOrderByUserId(Long userId){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());

        List<CartItemsDto> cartItemsDtoList = cartService.getCart(userId);
        orderDto.setCartItems(cartItemsDtoList);

        if(activeOrder.getCoupon() != null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;
    }

    @Override
    public OrderDto applyCoupon(Long userId, String code) {
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon = this.couponRepo.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not found"));

        if(couponExpired(coupon)){
            throw new ValidationException("coupon is expired");
        }

        if (activeOrder.getTotalAmount() < 20) {
            throw new ValidationException("Total amount must be greater than 20 to apply a coupon.");
        }

        double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;

        activeOrder.setAmount(netAmount);
        activeOrder.setCoupon(coupon);
        activeOrder.setDiscount(discountAmount);
        orderRepo.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    private boolean couponExpired(Coupon coupon) {
        Date currentDate = new Date();
        Date expirationDate = coupon.getExpirationDate();

        return expirationDate != null && currentDate.after(currentDate);
    }
}

