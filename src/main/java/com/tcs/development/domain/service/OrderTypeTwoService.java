package com.tcs.development.domain.service;

import com.tcs.development.domain.*;
import com.tcs.development.domain.models.Money;
import com.tcs.development.domain.models.Order;
import com.tcs.development.domain.models.OrderItem;
import com.tcs.development.domain.models.Product;
import com.tcs.development.expose.dto.OrderItemDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderTypeTwoService implements OrderService {

	@Override
    public Mono<Order> createOrder(List<OrderItemDto> itemDtoList, OrderType type) {    	
        List<OrderItem> orderItems = new ArrayList<>();
                
        itemDtoList.stream().forEach(itemDto -> {
        	Money price = new Money();
            price.setCurrency(itemDto.getCurrency());
            price.setAmount(itemDto.getPrice());

            Product product = new Product();
            product.setProductId(itemDto.getProductId());
            product.setPrice(price);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());

            orderItems.add(orderItem);
        });
                
        
        Mono<Order> orderReact = Flux.fromIterable(orderItems).collectList().map(lst -> {
        	Order order = new Order();
            order.setStatus(OrderStatus.CREATED);
            order.setItems(orderItems);
            order.setCreateAt(LocalDateTime.now());
            order.setType(type);

            //Calculando total de monto
            BigDecimal total = lst.stream().map(x -> x.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
                        
            Money totalMoney = new Money();
            totalMoney.setAmount(total);
            totalMoney.setCurrency(Currency.PEN);
            order.setTotalAmount(totalMoney);

            this.applyingDiscount(order);
            
        	return order;
        	});
        
        return orderReact;
    }

    private void applyingDiscount(Order order) {
        log.info(">>>>> Este método es específico para las órdenes de tipo 2");
        log.info("================== Aplicando descuento ==================");
        BigDecimal discountedAmount = order.getTotalAmount().getAmount().multiply(new BigDecimal(0.8));
        Money discountedMoney = new Money();
        discountedMoney.setCurrency(Currency.PEN);
        discountedMoney.setAmount(discountedAmount);
        order.setTotalAmount(discountedMoney);
    }

	@Override
    public void cancelOrder(Long orderId) {

    }

	@Override
	public List<Product> findAllProductos() {
		// TODO Auto-generated method stub
		return null;
	}
}
