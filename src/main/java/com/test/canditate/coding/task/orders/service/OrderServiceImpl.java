package com.test.canditate.coding.task.orders.service;

import com.test.canditate.coding.task.orders.model.Order;
import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public ResponseOrder createOrder(ServiceOrder serviceOrder) {

        return ResponseOrder.builder()
                .orderList(serviceOrder.getOrders()).totalAmount(
                        getTotal(serviceOrder.orders)
                ).build();
    }

    private int getTotal(List<Order> orders) {
        Holder<Integer> total = new Holder<>(0);
        orders.stream().forEach(order -> {
                    total.value += order.quantity * order.getCost();
                }
        );
        return total.value;
    }
}