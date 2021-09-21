package com.test.canditate.coding.task.orders.service;

import com.test.canditate.coding.task.orders.io.gateway.ProductsRepository;
import com.test.canditate.coding.task.orders.model.Product;
import com.test.canditate.coding.task.orders.model.ResponseOrder;
import com.test.canditate.coding.task.orders.model.ServiceOrder;
import com.test.canditate.coding.task.products.io.gateway.OrdersRepository;
import com.test.canditate.coding.task.orders.model.data.OrderDB;
import com.test.canditate.coding.task.orders.model.data.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Service
public class OrderServiceImpl implements OrderService {

    private final String ORANGE = "ORANGES";
    private final String APPLE = "APPLES";
    private final int FREE_ORANGES = 2;
    private final int PROMOTION_ORANGES_TO_BUY = 3;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public ResponseOrder createOrder(ServiceOrder serviceOrder) {

        List <Product> updatedProducts =  new ArrayList<>();

        for (Product product : serviceOrder.getProducts()){
            Product newProduct = new Product();
            if (product.isActiveOffer() && product.getArticle().equalsIgnoreCase(APPLE)) {
                newProduct.setQuantity(product.getQuantity() * 2);
            } else {
                newProduct.setQuantity(product.getQuantity());
            }
            newProduct.setCost(product.getCost());
            newProduct.setArticle(product.getArticle());
            newProduct.setActiveOffer(product.isActiveOffer());
            updatedProducts.add(newProduct);
        }

        ResponseOrder order = ResponseOrder
                .builder()
                .productList(updatedProducts)
                .totalAmount(getTotal(serviceOrder.getProducts()))
                .build();

        OrderDB orderDB = new OrderDB();
        orderDB.setTotalAmount(order.getTotalAmount());
        ordersRepository.save(orderDB);

        for (Product createProduct : order.getProductList()){
            ProductDB productDB = new ProductDB();
            productDB.setActiveOffer(createProduct.isActiveOffer());
            productDB.setArticle(createProduct.getArticle());
            productDB.setCost(createProduct.getCost());
            productDB.setQuantity(createProduct.getQuantity());
            productDB.setOrderDB(orderDB);
            productsRepository.save(productDB);
        }

        order.setOrderId(order.getOrderId());
        return order;
    }

    @Override
    public OrderDB getOrder(Long orderId) {
        return ordersRepository.getById(orderId);
    }

    @Override
    public List<OrderDB> getOrders() {
        return ordersRepository.findAll();
    }

    private int getTotal(List<Product> products) {
        Holder<Integer> total = new Holder<>(0);
        products.stream().forEach(order -> {

                    if (order.isActiveOffer() && order.getArticle().equalsIgnoreCase(APPLE)) {
                        total.value += abs((order.quantity * order.getCost()) / 2);
                    } else if (order.isActiveOffer() && order.getArticle().equalsIgnoreCase(ORANGE)) {
                        int mod = order.getQuantity() % PROMOTION_ORANGES_TO_BUY;
                        int  oranges =  (( order.getQuantity()/PROMOTION_ORANGES_TO_BUY * FREE_ORANGES) + mod );
                        total.value += oranges * order.getCost();
                    } else {
                        total.value += order.quantity * order.getCost();
                    }
                }
        );
        return total.value;
    }
}