package com.tcs.development.domain.service;

import com.tcs.development.domain.*;
import com.tcs.development.expose.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTypeOneService implements OrderService {

    @Override
    public Order createOrder(List<OrderItemDto> itemDtoList, OrderType type) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto itemDto : itemDtoList) {
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
        }
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setItems(orderItems);
        order.setCreateAt(LocalDateTime.now());
        order.setType(type);
        //Calculando total de monto
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            BigDecimal subTotal = orderItem.getProduct().getPrice().getAmount().multiply(new BigDecimal(orderItem.getQuantity()));
            total = total.add(subTotal);
        }

        System.out.println("guardando orden de tipo: " + order.getType());

        Money totalMoney = new Money();
        totalMoney.setAmount(total);
        totalMoney.setCurrency(Currency.PEN);
        order.setTotalAmount(totalMoney);

        this.metodoExclusivoParaOrdenesTipo1(order);

        return order;
    }

    private void metodoExclusivoParaOrdenesTipo1(final Order order) {
        System.out.println(">>>>> Este método es específico para las órdenes de tipo 1");
        System.out.println("guardando orden de tipo: " + order.getType());
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
