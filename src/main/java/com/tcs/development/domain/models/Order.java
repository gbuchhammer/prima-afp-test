package com.tcs.development.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import com.tcs.development.domain.OrderStatus;
import com.tcs.development.domain.OrderType;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "order")
@Setter
@Getter
public class Order {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "order_id")
    private Long orderId;
//    @Column(name = "create_at")
    private LocalDateTime createAt;
//    @Column(name = "total_amount")
    private Money totalAmount;
    private List<OrderItem> items;

//    @Transient
    private OrderStatus status;
//    @Transient
    private OrderType type;

}
