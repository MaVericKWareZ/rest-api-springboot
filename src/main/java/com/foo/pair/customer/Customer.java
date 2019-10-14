package com.foo.pair.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foo.pair.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CURRENCY")
    private String currency;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Builder.Default
    @JsonManagedReference
    private Set<Order> orderSet = new HashSet<>(0);

}

