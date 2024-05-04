package africashop.be.services.Member;

import africashop.be.dtos.ProductCartDto;
import org.springframework.http.ResponseEntity;

public interface CartService {

    public ResponseEntity<?> addToCart(ProductCartDto productCartDto);
}
