package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.CouponDto;
import africashop.be.entities.Coupon;
import africashop.be.exceptions.ValidationException;
import africashop.be.services.Admin.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/createCoupon")
    ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
        try{
            Coupon coupon1 = couponService.createCoupon(coupon);
            return ResponseEntity.ok(coupon1);
        }catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>>getAllCoupons(){
        return ResponseEntity.ok(couponService.getAllCoupon());}

    @DeleteMapping("/coupon/{id}")
    public void deleteCoupon(@PathVariable Long id){
        couponService.deleteCoupon(id);
    }

    @PutMapping("/coupon/update/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Long id, @ModelAttribute Coupon coupon) throws IOException {
        Coupon coupon1 = couponService.updateCoupon(id,coupon);
        if(coupon1 != null){
            return ResponseEntity.ok(coupon1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}


