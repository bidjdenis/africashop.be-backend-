package africashop.be.Repositories;

import africashop.be.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long> {
    boolean existsByCode(String code);

   Optional<Coupon>  findByCode(String code);
}
