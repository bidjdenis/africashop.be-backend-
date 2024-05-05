package africashop.be.services.Member;

import africashop.be.dtos.ProductCartDto;
import africashop.be.dtos.WishlistDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WishlistService {

    ResponseEntity<?> addToWishList(ProductCartDto productCartDto);
    List<WishlistDto> getWishListByUserId(Long userId);

}
