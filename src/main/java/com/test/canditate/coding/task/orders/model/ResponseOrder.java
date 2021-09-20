package com.test.canditate.coding.task.orders.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ResponseOrder {
    private List<Order> orderList;
    private int totalAmount;
}
