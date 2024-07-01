package africashop.be.services.Admin;

import africashop.be.Repositories.OrderRepo;
import africashop.be.dtos.OrderDto;
import africashop.be.entities.Order;
import africashop.be.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    private final OrderRepo orderRepo;
    public List<OrderDto> getAllPlacedOrders(){

        List<Order> orderList = orderRepo.
                findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered,
                        OrderStatus.Canceled));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();

            if(Objects.equals(status, "Shipped")){
                order.setOrderStatus(OrderStatus.Shipped);
            }else if(Objects.equals(status, "Delivered")){
                order.setOrderStatus(OrderStatus.Delivered);
            }else if(Objects.equals(status, "Canceled")) {
                order.setOrderStatus(OrderStatus.Canceled);
            }
            return orderRepo.save(order).getOrderDto();
        }
        return null;
    }
}
