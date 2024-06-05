package africashop.be.services.Member;

import africashop.be.Repositories.CouponRepo;
import africashop.be.entities.Coupon;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CouponsServiceImpl implements CouponsService{

    private final CouponRepo couponRepo;
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }
}
