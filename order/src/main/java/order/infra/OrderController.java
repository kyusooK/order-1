package order.infra;
import order.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/orders")
@Transactional
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/orders/placeorder",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public Order placeorder(HttpServletRequest request, HttpServletResponse response, 
        @RequestBody PlaceorderCommand placeorderCommand) throws Exception {
            System.out.println("##### /order/placeorder  called #####");
            Order order = new Order();
            order.placeorder(placeorderCommand);
            orderRepository.save(order);
            return order;
    }
    @RequestMapping(value = "/orders/{id}/updateorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order updateOrder(@PathVariable(value = "id")Long id, @RequestBody UpdateOrderCommand updateOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/updateOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.updateOrder(updateOrderCommand);
            
            orderRepository.save(order);
            return order;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
