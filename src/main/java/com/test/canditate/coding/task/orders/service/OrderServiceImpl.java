package com.test.canditate.coding.task.orders.service;

import com.test.canditate.coding.task.orders.model.Order;
import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Service
public class OrderServiceImpl implements OrderService {

    private final String ORANGE = "ORANGES";
    private final String APPLE = "APPLES";
    private final int FREE_ORANGES = 2;
    private final int PROMOTION_ORANGES_TO_BUY = 3;

    @Override
    public ResponseOrder createOrder(ServiceOrder serviceOrder) {

        List <Order> updatedOrders =  new ArrayList<>();

        for (Order order : serviceOrder.getOrders()){
            Order newOrder = new Order();
            if (order.isActiveOffer() && order.getArticle().equalsIgnoreCase(APPLE)) {
                newOrder.setQuantity(order.getQuantity() * 2);
                newOrder.setCost(order.getCost());
                newOrder.getArticle();
            }
            updatedOrders.add(newOrder);
        }

        return ResponseOrder
                .builder()
                .orderList(updatedOrders)
                .totalAmount(getTotal(serviceOrder.getOrders()))
                .build();
    }

    private int getTotal(List<Order> orders) {
        Holder<Integer> total = new Holder<>(0);
        orders.stream().forEach(order -> {

                    if (order.isActiveOffer() && order.getArticle().equalsIgnoreCase(APPLE)) {
                        total.value += abs((order.quantity * order.getCost()) / 2);
                    } else if (order.isActiveOffer() && order.getArticle().equalsIgnoreCase(ORANGE)) {
                        int mod = order.getQuantity() % PROMOTION_ORANGES_TO_BUY;
                        int  oranges =  (( order.getQuantity()/PROMOTION_ORANGES_TO_BUY * FREE_ORANGES) + mod );
                        total.value += oranges * order.getCost();
                    } else {
                        total.value += order.quantity * order.getCost();
                    }
                }
        );
        return total.value;
    }
}