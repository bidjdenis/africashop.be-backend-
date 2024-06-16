package africashop.be.Repositories;

import africashop.be.entities.Order;
import africashop.be.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    boolean existsByUserIdAndOrderStatus(Long userId, OrderStatus pending);

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus pending);

    List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> placed);
}
