package com.test.canditate.coding.task.orders.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products_db")
@Data
public class ProductDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_Id", unique = true, nullable = false)
    private Long productId;

    public String article;
    public int cost;
    public int quantity;
    public boolean activeOffer;

    @JsonIgnore
    public OrderDB getOrderDB() {
        return orderDB;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_Id", nullable = false)
    private OrderDB orderDB;
}
