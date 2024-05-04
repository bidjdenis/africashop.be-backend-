package africashop.be.Repositories;

import africashop.be.entities.CartItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepo extends JpaRepository<CartItems,Long> {

    List<CartItems> findByUserId(Long userId);

    Optional<CartItems> findByProductIdAndUserId(Long productId, Long userId);
}
