package com.foo.pair.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderDTO> findAllOrders() {
        return orderMapper.map(orderRepository.findAll());
    }

    public OrderDTO findOrderById(Integer orderId) {
        return orderMapper.map(orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new));
    }

    public OrderDTO createOrder(Integer orderId, OrderDTO orderDTO) {
        Optional<Order> result = orderRepository.findById(orderId);
        if (result.isPresent()) {
            throw new EntityExistsException();
        }
        log.info("Created order , orderId = " + orderId);
        return orderMapper.map(orderRepository.save(orderMapper.map(orderDTO)));
    }

    public OrderDTO updateOrder(Integer orderId, OrderDTO updateOrderDTO) {
        Order orderToUpdate = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        if (Objects.nonNull(orderToUpdate)) {
            orderToUpdate = orderMapper.map(updateOrderDTO);
            return orderMapper.map(orderRepository.save(orderToUpdate));
        }
        log.info("Updated order , orderId = " + orderId);
        return OrderDTO.builder().build();
    }

    public OrderDTO deleteOrder(Integer orderId) {
        Order orderToDelete = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        orderRepository.delete(orderToDelete);
        log.info("Deleted order , orderId = " + orderId);
        return orderMapper.map(orderToDelete);
    }
}
