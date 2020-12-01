package com.tcs.development.expose.dto;

import com.tcs.development.domain.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDto {

    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private Currency currency;
}
