package africashop.be.services.Admin;

import africashop.be.Repositories.OrderRepo;
import africashop.be.dtos.OrderDto;
import africashop.be.entities.Order;
import africashop.be.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
