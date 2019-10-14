package com.foo.pair.customer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Integer customerId;

    private String customerName;

    private String email;

    private String address;

    private String currency;

}
