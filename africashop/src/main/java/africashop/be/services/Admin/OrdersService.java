package africashop.be.services.Admin;

import africashop.be.dtos.OrderDto;

import java.util.List;

public interface OrdersService {

    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

}
