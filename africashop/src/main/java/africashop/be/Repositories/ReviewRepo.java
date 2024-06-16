package africashop.be.Repositories;

import africashop.be.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Long> {
}
