package com.foo.pair.order;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = Order.class)
public interface OrderMapper {

//    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDTO map(Order source);

    @InheritInverseConfiguration
    Order map(OrderDTO target);

    default List<OrderDTO> map(List<Order> list) {
        return list.stream().map(this::map).collect(Collectors.toList());
    }

    default Page<OrderDTO> map(Page<Order> page) {
        return page.map(this::map);
    }
}
