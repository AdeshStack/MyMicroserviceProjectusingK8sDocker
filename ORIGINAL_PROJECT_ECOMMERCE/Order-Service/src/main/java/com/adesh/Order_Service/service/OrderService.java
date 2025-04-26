package com.adesh.Order_Service.service;


import com.adesh.Order_Service.model.OrderRequest;

public interface OrderService {


    long placeOrder(OrderRequest orderRequest);
}
