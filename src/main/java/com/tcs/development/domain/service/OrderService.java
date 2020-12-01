package com.tcs.development.domain.service;

import com.tcs.development.domain.Order;
import com.tcs.development.domain.OrderType;
import com.tcs.development.expose.dto.OrderItemDto;

import java.util.List;

public interface OrderService {

    Order createOrder(List<OrderItemDto> itemDtoList, OrderType type);

    void cancelOrder(Long orderId);

}
