package africashop.be.Repositories;

import africashop.be.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepo extends JpaRepository<Wishlist,Long> {
    Optional<Wishlist> findByProductIdAndUserId(Long productId, Long userId);
}
