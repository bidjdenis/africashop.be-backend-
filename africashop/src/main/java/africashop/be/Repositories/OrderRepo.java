package africashop.be.Repositories;

import africashop.be.entities.Order;
import africashop.be.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
    boolean existsByUserIdAndOrderStatus(Long userId, OrderStatus pending);

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus pending);
}
