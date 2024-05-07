package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.ProductCartDto;
import africashop.be.dtos.WishlistDto;
import africashop.be.services.Member.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/addWishlist")
    public ResponseEntity<?> addToWishlist(@RequestBody ProductCartDto productCartDto){
        return wishlistService.addToWishList(productCartDto);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
        List<WishlistDto> wishListDtos  = wishlistService.getWishListByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(wishListDtos);
    }

    @DeleteMapping("/remove/wishlist/{id}")
    public ResponseEntity<?> deleteProductFromWishlist(@PathVariable Long id){
        wishlistService.removeProductFromWishlist(id);
        return ResponseEntity.ok().build();
    }
}
