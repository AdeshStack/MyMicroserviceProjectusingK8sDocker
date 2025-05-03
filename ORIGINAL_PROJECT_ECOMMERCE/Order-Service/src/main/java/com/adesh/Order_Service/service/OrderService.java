package com.adesh.Order_Service.service;


import com.adesh.Order_Service.model.OrderRequest;
import com.adesh.Order_Service.model.OrderResponse;

public interface OrderService {


    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
