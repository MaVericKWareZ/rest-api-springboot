package com.foo.pair.order;

import com.foo.pair.customer.Customer;
import com.foo.pair.customer.CustomerDTO;
import com.foo.pair.customer.CustomerMapper;
import com.foo.pair.customer.CustomerService;
import com.foo.pair.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private CustomerService customerService;
    private CustomerMapper customerMapper;

    public List<OrderDTO> findAllOrders() {
        return orderMapper.map(orderRepository.findAll());
    }

    public OrderDTO findOrderById(Integer orderId) {
        return orderMapper.map(orderRepository.findById(orderId).orElseThrow(() -> new BadRequestException("Order Not found with orderId = " + orderId)));
    }

    public List<OrderDTO> findOrdersByCustomerId(Integer customerId) throws BadRequestException {
        CustomerDTO customerDTO = customerService.findCustomerById(customerId);
        return orderMapper.map(orderRepository.findAllByCustomer(customerMapper.map(customerDTO)));
    }

    public OrderDTO createOrderForCustomer(Integer customerId, OrderDTO orderDTO) throws BadRequestException {
        Customer customer = customerMapper.map(customerService.findCustomerById(customerId));
        Order order = orderMapper.map(orderDTO);
        order.setCustomer(customer);
        return orderMapper.map(orderRepository.save(order));
    }
}
