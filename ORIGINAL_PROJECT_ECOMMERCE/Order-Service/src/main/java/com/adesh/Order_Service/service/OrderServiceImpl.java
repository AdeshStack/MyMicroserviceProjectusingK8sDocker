package com.adesh.Order_Service.service;


import com.adesh.Order_Service.entity.Order;
import com.adesh.Order_Service.external.client.ProductService;
import com.adesh.Order_Service.model.OrderRequest;
import com.adesh.Order_Service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository repo;

    @Autowired
    private ProductService productService;


    @Override
    public long placeOrder(OrderRequest orderRequest) {

        //1 save the data with status order created
        //product service -> block products (reduce the  quantity)
        //payment service -> payment ->success -> completed,else cancelled

        log.info("Placing order request: {}",orderRequest);

        //api from product service
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        Order order=Order.builder()
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .build();

        long orderId=this.repo.save(order).getId();
        log.info("Order places successfully with Order Id: {}",orderId);
        return orderId;


    }
}
