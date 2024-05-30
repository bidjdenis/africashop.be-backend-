package africashop.be.services.Member;

import africashop.be.Repositories.CartItemsRepo;
import africashop.be.Repositories.OrderRepo;
import africashop.be.Repositories.ProductRepo;
import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.CartItemsDto;
import africashop.be.entities.CartItems;
import africashop.be.entities.Order;
import africashop.be.entities.OrderItems;
import africashop.be.entities.User;
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

    }

