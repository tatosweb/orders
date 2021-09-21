package com.test.canditate.coding.task.orders.service;

import com.test.canditate.coding.task.orders.model.Order;
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
public class OrderServiceTest {

    @Autowired
    private OrderServiceImpl service;

    @Test
    public void createOrderTest() {

        List<Order> orders = new ArrayList<>();

        Order orange = new Order();
        orange.setArticle("orange");
        orange.setCost(25);
        orange.setQuantity(1);
        orange.setActiveOffer(false);

        Order apple = new Order();
        apple.setArticle("apple");
        apple.setCost(60);
        apple.setQuantity(1);
        apple.setActiveOffer(false);

        orders.add(apple);
        orders.add(orange);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setOrders(orders);

        ResponseOrder responseOrder = service.createOrder(serviceOrder);
        Assert.assertNotNull(responseOrder);
        Assert.assertEquals(85, responseOrder.getTotalAmount());
    }

    @Test
    public void createOrderPromotionTest() {

        List<Order> orders = new ArrayList<>();

        Order orange = new Order();
        orange.setArticle("orange");
        orange.setCost(25);
        orange.setQuantity(3);
        orange.setActiveOffer(true);

        Order apple = new Order();
        apple.setArticle("apple");
        apple.setCost(60);
        apple.setQuantity(1);
        apple.setActiveOffer(true);

        orders.add(apple);
        orders.add(orange);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setOrders(orders);

        ResponseOrder responseOrder = service.createOrder(serviceOrder);
        Assert.assertNotNull(responseOrder);
        Assert.assertEquals(135, responseOrder.getTotalAmount());
    }
}
