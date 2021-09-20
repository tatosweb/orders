package com.test.canditate.coding.task.orders.model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class ServiceOrder implements Serializable {
    public List<Order> orders;
}
