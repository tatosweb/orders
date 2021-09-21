package com.test.canditate.coding.task.orders.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_db")
@Data
public class OrderDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_Id", unique = true, nullable = false)
    private Long orderId;

    private int totalAmount;

    @JsonIgnore
    public Set<ProductDB> getProducts() {
        return products;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "orderDB", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductDB> products;
}
