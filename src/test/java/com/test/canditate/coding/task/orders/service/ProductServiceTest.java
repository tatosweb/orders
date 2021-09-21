package com.test.canditate.coding.task.products.service;

import com.test.canditate.coding.task.orders.service.OrderServiceImpl;
import com.test.canditate.coding.task.orders.model.Product;
import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private OrderServiceImpl service;

    @Test
    public void createOrderTest() {

        List<Product> products = new ArrayList<>();

        Product orange = new Product();
        orange.setArticle("orange");
        orange.setCost(25);
        orange.setQuantity(1);
        orange.setActiveOffer(false);

        Product apple = new Product();
        apple.setArticle("apple");
        apple.setCost(60);
        apple.setQuantity(1);
        apple.setActiveOffer(false);

        products.add(apple);
        products.add(orange);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setProducts(products);

        ResponseOrder responseOrder = service.createOrder(serviceOrder);
        Assert.assertNotNull(responseOrder);
        Assert.assertEquals(85, responseOrder.getTotalAmount());
    }

    @Test
    public void createOrderPromotionTest() {

        List<Product> products = new ArrayList<>();

        Product orange = new Product();
        orange.setArticle("orange");
        orange.setCost(25);
        orange.setQuantity(3);
        orange.setActiveOffer(true);

        Product apple = new Product();
        apple.setArticle("apple");
        apple.setCost(60);
        apple.setQuantity(1);
        apple.setActiveOffer(true);

        products.add(apple);
        products.add(orange);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setProducts(products);

        ResponseOrder responseOrder = service.createOrder(serviceOrder);
        Assert.assertNotNull(responseOrder);
        Assert.assertEquals(135, responseOrder.getTotalAmount());
    }
}
