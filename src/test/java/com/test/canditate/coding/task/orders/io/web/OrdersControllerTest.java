package com.test.canditate.coding.task.products.io.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.canditate.coding.task.orders.model.Product;
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

        ResponseOrder responseOrder = ResponseOrder.builder().totalAmount(85).productList(products).build();
        Mockito.when(service.createOrder(ArgumentMatchers.any())).thenReturn(responseOrder);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setProducts(products);

        String json = mapper.writeValueAsString(serviceOrder);

        mockMvc.perform(post("/api/v1/create-order").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalAmount", Matchers.equalTo(85)));

    }


    @Test
    public void createOrderPromotionTest() throws Exception {

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

        ResponseOrder responseOrder = ResponseOrder.builder().totalAmount(135).productList(products).build();
        Mockito.when(service.createOrder(ArgumentMatchers.any())).thenReturn(responseOrder);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setProducts(products);

        String json = mapper.writeValueAsString(serviceOrder);

        mockMvc.perform(post("/api/v1/create-order").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalAmount", Matchers.equalTo(135)));

    }
}
