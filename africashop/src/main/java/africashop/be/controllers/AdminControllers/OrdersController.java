package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.AnalyticsResponse;
import africashop.be.dtos.OrderDto;
import africashop.be.services.Admin.OrdersService;
import africashop.be.services.Admin.OrdersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersServiceImpl service;

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(ordersService.getAllPlacedOrders());
    }

    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> orderStatusChange(@PathVariable Long orderId, @PathVariable String status){
        OrderDto orderDto = ordersService.changeOrderStatus(orderId, status);
        if(orderDto == null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics(){
        return ResponseEntity.ok(service.calculateAnalytics());
    }
}
