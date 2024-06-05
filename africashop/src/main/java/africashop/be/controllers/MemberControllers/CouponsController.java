package africashop.be.controllers.MemberControllers;

import africashop.be.entities.Coupon;
import africashop.be.services.Member.CouponsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class CouponsController {
    private final CouponsService couponsService;

    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> coupons = couponsService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }
}
