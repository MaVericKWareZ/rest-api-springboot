package com.foo.pair.order;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Integer orderId, @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrderDTO = orderService.createOrder(orderId, orderDTO);
        //TODO : return created uri
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(PATH_ID_URL)
                .buildAndExpand(createdOrderDTO.getOrderId())
                .toUri();
        return ResponseEntity.ok(createdOrderDTO);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer orderId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrderDTO = orderService.updateOrder(orderId, orderDTO);
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable Integer orderId) {
        OrderDTO deletedOrderDTO = orderService.deleteOrder(orderId);
        return ResponseEntity.ok(deletedOrderDTO);
    }


}
