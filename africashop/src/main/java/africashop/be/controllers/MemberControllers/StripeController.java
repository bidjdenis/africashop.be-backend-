package africashop.be.controllers.MemberControllers;

import africashop.be.Repositories.OrderRepo;
import africashop.be.dtos.CheckoutSessionRequest;
import africashop.be.dtos.OrderDto;
import africashop.be.entities.Order;
import africashop.be.enums.OrderStatus;
import africashop.be.exceptions.ValidationException;
import africashop.be.services.Member.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class StripeController {

    private final OrderService orderService;

    private final OrderRepo orderRepo;

    static {
        Stripe.apiKey = "sk_test_51N9YTaHc6KU9WzTtuX9QAU3Iq0TIFTRLJbUrpOBlaaxKbKEV9Y7S8ZdmxLufHCKUxjJQx5V8fd8rmLX4iFO248QS00b8Hbq89N";
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String,String>> createChecKoutSession(@RequestBody CheckoutSessionRequest request){

        Long userId = request.getUserId();
        if (userId == null) {
            throw new ValidationException("User not found");
        }
        OrderDto orders = orderService.getOrderByUserId(userId);

        BigDecimal totalPrice = BigDecimal.ZERO;

        Order order1 = orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        totalPrice = totalPrice.add(BigDecimal.valueOf(order1.getAmount()));

        Map<String, String> response = new HashMap<>();

        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:4200/member/orders")
                    .setCancelUrl("http://localhost:4200/cancel")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("eur")
                                                    .setUnitAmount(totalPrice.multiply(new BigDecimal(100)).longValue())
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Items purchasse")
                                                                    .build())
                                                    .build())

                                    .setQuantity(1L)
                                    .build())
                    .putMetadata("userId", String.valueOf(userId))
                    .putMetadata("totalPrice", totalPrice.toString())
                    .build();

            Session session = Session.create(params);

            Order orderToUpdate = orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
            orderToUpdate.setOrderStatus(OrderStatus.Placed);
            orderRepo.save(orderToUpdate);

            response.put("id", session.getId());
            return ResponseEntity.ok(response);
        }catch (StripeException e){
            e.printStackTrace();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
