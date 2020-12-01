package com.tcs.development.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Money {

    private Currency currency;
    private BigDecimal amount;

}
