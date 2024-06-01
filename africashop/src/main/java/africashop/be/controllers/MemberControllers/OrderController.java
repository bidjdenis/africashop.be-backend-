package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.CartItemsDto;
import africashop.be.dtos.OrderDto;
import africashop.be.exceptions.ValidationException;
import africashop.be.services.Member.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/{userId}")
    public ResponseEntity<?> getValidationOrder(@PathVariable Long userId){
        List<CartItemsDto> cartItemsDtoList = orderService.validationOrder(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemsDtoList);

    }

    @GetMapping("/cart/order/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable Long userId){
        OrderDto orderDto = orderService.getOrderByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try {
            OrderDto orderDto = this.orderService.applyCoupon(userId,code);
            return ResponseEntity.ok(orderDto);
        } catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
