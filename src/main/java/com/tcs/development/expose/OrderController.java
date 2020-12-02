package com.tcs.development.expose;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tcs.development.domain.OrderType;
import com.tcs.development.domain.models.Order;
import com.tcs.development.domain.models.Product;
import com.tcs.development.domain.service.OrderService;
import com.tcs.development.expose.dto.OrderItemDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderTypeOneService;
	
    private final OrderService orderTypeTwoService;

    public void createOrder(List<OrderItemDto> itemDtoList, OrderType orderType) {
    	if (orderType == OrderType.TYPE_ONE) {
    		log.info("============ Creating order of type one =================");
            Mono<Order> orderTypeOne = this.orderTypeOneService.createOrder(itemDtoList, orderType);
            log.info("============ Order Created =================");
            //System.out.println("<> Order with total: " + orderTypeOne.getTotalAmount().getAmount());
            orderTypeOne.subscribe(obj -> System.out.println("<> Order with total: " + obj.getTotalAmount().getAmount()));
        } else if (orderType == OrderType.TYPE_TWO) {
        	log.info("============ Creating order of type two =================");
            Mono<Order> orderTypeTwo = this.orderTypeTwoService.createOrder(itemDtoList, orderType);
            log.info("============ Order Created =================");
            orderTypeTwo.subscribe(obj -> System.out.println("<> Order with total: " + obj.getTotalAmount().getAmount()));
            
        } else {
            throw new UnsupportedOperationException("Unsupported for type: " + orderType);
        }
    }
    
    public List<Product> findAllProducts() {
    	return orderTypeOneService.findAllProductos();
    }

}
