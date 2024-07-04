package africashop.be.services.Admin;

import africashop.be.Repositories.OrderRepo;
import africashop.be.dtos.AnalyticsResponse;
import africashop.be.dtos.OrderDto;
import africashop.be.entities.Order;
import africashop.be.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    private final OrderRepo orderRepo;
    public List<OrderDto> getAllPlacedOrders(){

        List<Order> orderList = orderRepo.
                findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered,
                        OrderStatus.Canceled));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();

            if(Objects.equals(status, "Shipped")){
                order.setOrderStatus(OrderStatus.Shipped);
            }else if(Objects.equals(status, "Delivered")){
                order.setOrderStatus(OrderStatus.Delivered);
            }else if(Objects.equals(status, "Canceled")) {
                order.setOrderStatus(OrderStatus.Canceled);
            }
            return orderRepo.save(order).getOrderDto();
        }
        return null;
    }

    public AnalyticsResponse calculateAnalytics(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);

        Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthEarnings = getTotalEarningsForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());


        Long placed = orderRepo.countByOrderStatus(OrderStatus.Placed);
        Long shipped = orderRepo.countByOrderStatus(OrderStatus.Shipped);
        Long delivered = orderRepo.countByOrderStatus(OrderStatus.Delivered);

        return new AnalyticsResponse(placed, shipped, delivered, currentMonthOrders, previousMonthOrders,
                currentMonthEarnings, previousMonthEarnings);
    }

    public Long getTotalOrdersForMonth(int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepo.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        return (long) orders.size();
    }

    public Long getTotalEarningsForMonth(int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepo.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        Double sum = 0.0;
        for (Order order: orders) {
            sum += order.getAmount();
        }
        return (long) sum.doubleValue();
    }

    }

