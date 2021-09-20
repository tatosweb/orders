package com.test.canditate.coding.task.orders.io.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.canditate.coding.task.orders.model.Order;
import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import com.test.canditate.coding.task.orders.service.OrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createOrderTest() throws Exception {

        List<Order> orders = new ArrayList<>();
        Order orange = new Order();
        orange.setArticle("orange");
        orange.setCost(25);
        orange.setQuantity(1);

        Order apple = new Order();
        apple.setArticle("apple");
        apple.setCost(60);
        apple.setQuantity(1);

        orders.add(apple);
        orders.add(orange);

        ResponseOrder responseOrder = ResponseOrder.builder().totalAmount(85).orderList(orders).build();
        Mockito.when(service.createOrder(ArgumentMatchers.any())).thenReturn(responseOrder);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setOrders(orders);

        String json = mapper.writeValueAsString(serviceOrder);

        mockMvc.perform(post("/api/v1/create-order").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalAmount", Matchers.equalTo(85)));

    }
}
