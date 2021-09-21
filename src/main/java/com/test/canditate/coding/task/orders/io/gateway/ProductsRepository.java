package com.test.canditate.coding.task.orders.io.gateway;

import com.test.canditate.coding.task.orders.model.data.ProductDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductDB, Long> {

}
