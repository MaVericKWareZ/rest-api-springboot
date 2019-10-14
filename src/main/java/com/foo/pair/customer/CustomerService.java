package com.foo.pair.customer;

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
public class CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    public List<CustomerDTO> findAllCustomers() {
        return customerMapper.map(customerRepository.findAll());
    }

    public CustomerDTO findCustomerById(Integer customerId) {
        return customerMapper.map(customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new));
    }

    public CustomerDTO createCustomer(Integer customerId, CustomerDTO customer) {
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            throw new EntityExistsException();
        }
        log.info("Created customer , customerId = " + customerId);
        return customerMapper.map(customerRepository.save(customerMapper.map(customer)));
    }

    public CustomerDTO updateCustomer(Integer customerIdToUpdate, CustomerDTO updatedCustomerDTO) {
        Customer customerToUpdate = customerRepository.findById(customerIdToUpdate).orElseThrow(EntityNotFoundException::new);
        if (Objects.nonNull(customerToUpdate)) {
            customerToUpdate = customerMapper.map(updatedCustomerDTO);
            return customerMapper.map(customerRepository.save(customerToUpdate));
        }
        log.info("Updated customer , customerId = " + customerIdToUpdate);
        return CustomerDTO.builder().build();
    }

    public CustomerDTO deleteCustomer(Integer customerId) {
        Customer customerToDelete = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
        customerRepository.delete(customerToDelete);
        log.info("Deleted customer , customerId = " + customerId);
        return customerMapper.map(customerToDelete);
    }
}
