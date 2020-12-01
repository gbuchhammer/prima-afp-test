package com.tcs.development.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long productId;
    private String name;
    private Money price;

}
