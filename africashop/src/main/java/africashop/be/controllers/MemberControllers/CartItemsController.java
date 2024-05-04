package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.ProductCartDto;
import africashop.be.services.Member.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class CartItemsController {

    private final CartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<?> addToCart(@RequestBody ProductCartDto productCartDto){
        return cartService.addToCart(productCartDto);
    }
}
