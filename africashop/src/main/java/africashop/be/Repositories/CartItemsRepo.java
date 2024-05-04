package africashop.be.Repositories;

import africashop.be.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
    CartItems findByUserIdAndProductId(Long userId, Long productId);

    List<CartItems> findByUserId(Long userId);
}
