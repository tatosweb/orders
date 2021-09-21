package com.test.canditate.coding.task.orders.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ResponseOrder {
    private List<Product> productList;
    private int totalAmount;
    private Long orderId;
}
