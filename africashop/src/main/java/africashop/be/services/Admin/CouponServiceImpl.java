package africashop.be.services.Admin;

import africashop.be.Repositories.CouponRepo;
import africashop.be.dtos.CouponDto;
import africashop.be.entities.Coupon;
import africashop.be.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteCoupon(Long id) {
        couponRepo.deleteById(id);
    }

    @Override
    public Coupon updateCoupon(Long id, Coupon coupon) throws IOException {
        Optional<Coupon> optionalCoupon = couponRepo.findById(id);
        if(optionalCoupon.isPresent()){
            Coupon coupon1 = optionalCoupon.get();

            coupon1.setName(coupon.getName());
            coupon1.setCode(coupon.getCode());
            coupon1.setDiscount(coupon.getDiscount());
            coupon1.setExpirationDate(coupon.getExpirationDate());
            return couponRepo.save(coupon1);
        }
        return null;
    }
}
