package com.tcs.development.domain.service;

import com.tcs.development.dao.ProductDao;
import com.tcs.development.domain.*;
import com.tcs.development.domain.models.Money;
import com.tcs.development.domain.models.Order;
import com.tcs.development.domain.models.OrderItem;
import com.tcs.development.domain.models.Product;
import com.tcs.development.expose.dto.OrderItemDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class OrderTypeOneService implements OrderService {

	@Autowired
	private ProductDao productoDao;
	
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

            this.metodoExclusivoParaOrdenesTipo1(order);
            
        	return order;
        	});
        
        return orderReact;
    }

    private void metodoExclusivoParaOrdenesTipo1(final Order order) {
        log.info(">>>>> Este método es específico para las órdenes de tipo 1");
        log.info("guardando orden de tipo: " + order.getType());
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAllProductos() {		
		return StreamSupport.stream(productoDao.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
