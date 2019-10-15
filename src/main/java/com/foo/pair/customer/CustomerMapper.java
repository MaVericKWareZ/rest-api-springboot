package com.foo.pair.customer;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = Customer.class)
public interface CustomerMapper {

    CustomerDTO map(Customer source);

    @InheritInverseConfiguration
    Customer map(CustomerDTO target);

    default List<CustomerDTO> map(List<Customer> list) {
        return list.stream().map(this::map).collect(Collectors.toList());
    }

    default Page<CustomerDTO> map(Page<Customer> page) {
        return page.map(this::map);
    }
}
