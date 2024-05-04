package africashop.be.services.Member;

import africashop.be.dtos.CartItemsDto;
import africashop.be.dtos.ProductCartDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    public ResponseEntity<?> addToCart(ProductCartDto productCartDto);

    public List<CartItemsDto> getCart(Long userId);

    List<CartItemsDto> increaseProductQuantity(ProductCartDto productCartDto);

}
