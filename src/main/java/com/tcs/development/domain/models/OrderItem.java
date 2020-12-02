package com.tcs.development.domain.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
	
    private Product product;
	
    private Integer quantity;
    
    public BigDecimal getTotal() {
    	return product.getPrice().getAmount().multiply(new BigDecimal(this.quantity));
    }

}
