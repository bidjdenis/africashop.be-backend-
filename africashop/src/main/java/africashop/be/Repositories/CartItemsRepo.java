package africashop.be.Repositories;

import africashop.be.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
}
