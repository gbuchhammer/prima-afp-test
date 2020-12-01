package com.tcs.development.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    private Product product;
    private Integer quantity;

}
