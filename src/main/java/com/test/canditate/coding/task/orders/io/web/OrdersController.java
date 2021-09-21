package com.test.canditate.coding.task.orders.io.web;

import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import com.test.canditate.coding.task.orders.model.data.OrderDB;
import com.test.canditate.coding.task.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1", produces = "application/json;charset=UTF-8")
public class OrdersController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-order")
    public ResponseOrder createOder(@RequestBody ServiceOrder serviceOrder) {
        return orderService.createOrder(serviceOrder);
    }

    @GetMapping("/order/{id}")
    public ResponseOrder getOrder(@PathVariable final Long id) {

        OrderDB orderDB = orderService.getOrder(id);

        ResponseOrder order = ResponseOrder
                .builder()
                .totalAmount(orderDB.getTotalAmount())
                .orderId(orderDB.getOrderId())
                .productList(new ArrayList<>())
                .build();

        return order;
    }

    @GetMapping("/orders/")
    public List<OrderDB> getOrders() {
        return orderService.getOrders();
    }
}
