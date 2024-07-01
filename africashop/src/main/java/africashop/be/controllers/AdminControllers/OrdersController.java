package africashop.be.controllers.AdminControllers;

import africashop.be.dtos.OrderDto;
import africashop.be.services.Admin.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(ordersService.getAllPlacedOrders());
    }
}
