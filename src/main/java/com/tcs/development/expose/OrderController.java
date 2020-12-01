package com.tcs.development.expose;

import com.tcs.development.domain.Order;
import com.tcs.development.domain.OrderType;
import com.tcs.development.domain.service.OrderTypeOneService;
import com.tcs.development.domain.service.OrderTypeTwoService;
import com.tcs.development.expose.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderController {

    private final OrderTypeOneService orderTypeOneService;
    private final OrderTypeTwoService orderTypeTwoService;

    public void createOrder(List<OrderItemDto> itemDtoList, OrderType orderType) {
        if (orderType == OrderType.TYPE_ONE) {
            System.out.println("============ Creating order of type one =================");
            Order orderTypeOne = this.orderTypeOneService.createOrder(itemDtoList, orderType);
            System.out.println("============ Order Created =================");
            System.out.println("<> Order with total: " + orderTypeOne.getTotalAmount().getAmount());
        } else if (orderType == OrderType.TYPE_TWO) {
            System.out.println("============ Creating order of type two =================");
            Order orderTypeTwo = this.orderTypeTwoService.createOrder(itemDtoList, orderType);
            System.out.println("============ Order Created =================");
            System.out.println("<> Order with total: " + orderTypeTwo.getTotalAmount().getAmount());
        } else {
            throw new UnsupportedOperationException("Unsupported for type: " + orderType);
        }
    }

}
