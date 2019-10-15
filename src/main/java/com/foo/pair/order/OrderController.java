package com.foo.pair.order;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private static final String PATH_ID_URL = "/{id}";
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> findAllOrders() {
        List<OrderDTO> orderListDTO = orderService.findAllOrders();
        return ResponseEntity.ok(orderListDTO);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Integer orderId) {
        OrderDTO orderDTO = orderService.findOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/orders/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> findOrdersForCustomer(@PathVariable Integer customerId) {
        List<OrderDTO> orderDTOList = orderService.findOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orderDTOList);
    }

    @PostMapping("/orders/customer/{customerId}")
    public ResponseEntity<OrderDTO> createOrderForCustomer(@PathVariable Integer customerId, @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrderForCustomer(customerId, orderDTO);
        return ResponseEntity.ok(createdOrder);
    }


}
