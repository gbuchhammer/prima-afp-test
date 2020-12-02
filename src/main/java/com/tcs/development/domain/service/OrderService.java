package com.tcs.development.domain.service;

import java.util.List;

import com.tcs.development.domain.OrderType;
import com.tcs.development.domain.models.Order;
import com.tcs.development.domain.models.Product;
import com.tcs.development.expose.dto.OrderItemDto;

import reactor.core.publisher.Mono;

public interface OrderService {
	
	List<Product> findAllProductos();

	Mono<Order> createOrder(List<OrderItemDto> itemDtoList, OrderType type);

    void cancelOrder(Long orderId);

}
