package com.tcs.development.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class Order {

    private Long orderId;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime createAt;
    private OrderType type;
    private Money totalAmount;

}
