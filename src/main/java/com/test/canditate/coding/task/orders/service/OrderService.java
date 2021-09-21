package com.test.canditate.coding.task.orders.service;

import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import com.test.canditate.coding.task.orders.model.data.OrderDB;

import java.util.List;

public interface OrderService {

    ResponseOrder createOrder (ServiceOrder serviceOrder);

    OrderDB getOrder (Long orderId);

    List<OrderDB> getOrders ();

}
