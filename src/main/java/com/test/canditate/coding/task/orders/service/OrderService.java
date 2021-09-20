package com.test.canditate.coding.task.orders.service;

import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;

public interface OrderService {

    ResponseOrder createOrder (ServiceOrder serviceOrder);
}
