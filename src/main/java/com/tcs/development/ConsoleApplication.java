package com.tcs.development;

import com.tcs.development.domain.Currency;
import com.tcs.development.domain.OrderType;
import com.tcs.development.expose.OrderController;
import com.tcs.development.expose.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ConsoleApplication implements CommandLineRunner {

    private final OrderController orderController;

    public static void main(String... args) {
        log.info("Running application");
        SpringApplication.run(ConsoleApplication.class, args);
        log.info("Finishing application");
    }

    @Override
    public void run(String... args) throws Exception {
        List<OrderItemDto> itemDtoList = new ArrayList<>();
        
        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setQuantity(13);
        itemDto.setCurrency(Currency.PEN);
        itemDto.setPrice(new BigDecimal(300));
        itemDto.setProductId(2L);
        itemDtoList.add(itemDto);

        itemDto = new OrderItemDto();
        itemDto.setQuantity(10);
        itemDto.setCurrency(Currency.PEN);
        itemDto.setPrice(new BigDecimal(500));
        itemDto.setProductId(5L);
        itemDtoList.add(itemDto);

        this.orderController.createOrder(itemDtoList, OrderType.TYPE_ONE);
        this.orderController.createOrder(itemDtoList, OrderType.TYPE_TWO);
    }

}
