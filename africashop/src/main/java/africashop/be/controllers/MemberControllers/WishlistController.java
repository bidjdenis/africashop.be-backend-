package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.ProductCartDto;
import africashop.be.services.Member.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/addWishlist")
    public ResponseEntity<?> addToWishlist(@RequestBody ProductCartDto productCartDto){
        return wishlistService.addToWishList(productCartDto);
    }
}
