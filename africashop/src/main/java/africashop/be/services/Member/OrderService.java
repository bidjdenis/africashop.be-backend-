package africashop.be.services.Member;

import africashop.be.dtos.CartItemsDto;

import java.util.List;

public interface OrderService {
    List<CartItemsDto> validationOrder(Long userId);
}
