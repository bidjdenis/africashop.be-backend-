package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.CartItemsDto;
import africashop.be.dtos.ProductCartDto;
import africashop.be.services.Member.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class CartItemsController {

    private final CartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<?> addToCart(@RequestBody ProductCartDto productCartDto){
        return cartService.addToCart(productCartDto);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CartItemsDto>> getCartItems(@PathVariable Long id){
        List<CartItemsDto> cartItemsDtoList = cartService.getCart(id);
        return ResponseEntity.ok(cartItemsDtoList);
    }
}
