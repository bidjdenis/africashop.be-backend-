package africashop.be.services.Admin;

import africashop.be.entities.Coupon;

import java.io.IOException;
import java.util.List;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupon();

    void deleteCoupon(Long id);

    Coupon updateCoupon(Long id, Coupon coupon) throws IOException;

}
