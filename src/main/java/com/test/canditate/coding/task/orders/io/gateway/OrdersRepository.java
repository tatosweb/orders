package com.test.canditate.coding.task.products.io.gateway;

import com.test.canditate.coding.task.orders.model.data.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderDB, Long> {

}
