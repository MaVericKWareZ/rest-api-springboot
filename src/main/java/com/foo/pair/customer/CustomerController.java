package com.foo.pair.customer;

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
public class CustomerController {

    private static final String PATH_ID_URL = "/{id}";
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
        List<CustomerDTO> customerList = customerService.findAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Integer customerId) {
        CustomerDTO customerDTO = customerService.findCustomerById(customerId);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> createCustomer(@PathVariable Integer customerId, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomerDTO = customerService.createCustomer(customerId, customerDTO);
        //TODO : return created uri
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(PATH_ID_URL)
                .buildAndExpand(createdCustomerDTO.getCustomerId())
                .toUri();
        return ResponseEntity.ok(createdCustomerDTO);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(updatedCustomerDTO);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Integer customerId) {
        CustomerDTO deletedCustomerDTO = customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(deletedCustomerDTO);
    }

}
