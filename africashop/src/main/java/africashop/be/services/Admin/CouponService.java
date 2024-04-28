package africashop.be.services.Admin;

import africashop.be.entities.Coupon;

import java.util.List;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupon();

}
