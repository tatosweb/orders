package com.test.canditate.coding.task.orders.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Order implements Serializable {
    public String article;
    public int cost;
    public int quantity;
    public boolean activeOffer;
}

