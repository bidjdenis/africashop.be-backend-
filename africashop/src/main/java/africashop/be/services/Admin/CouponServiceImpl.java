package africashop.be.services.Admin;

import africashop.be.Repositories.CouponRepo;
import africashop.be.entities.Coupon;
import africashop.be.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    private final CouponRepo couponRepo;

    public CouponServiceImpl(CouponRepo couponRepo) {
        this.couponRepo = couponRepo;
    }


    @Override
    public Coupon createCoupon(Coupon coupon) {
        if(couponRepo.existsByCode(coupon.getCode())){
            throw new ValidationException("This code already exists");
        }
        return couponRepo.save(coupon);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponRepo.findAll();
    }
}
