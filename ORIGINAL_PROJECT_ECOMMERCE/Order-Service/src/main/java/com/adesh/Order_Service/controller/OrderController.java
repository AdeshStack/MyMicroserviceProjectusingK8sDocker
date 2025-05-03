package com.adesh.Order_Service.controller;


import com.adesh.Order_Service.model.OrderRequest;
import com.adesh.Order_Service.model.OrderResponse;
import com.adesh.Order_Service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){

        long orderId= orderService.placeOrder(orderRequest);

        log.info("Order Id: {}", orderId);

        return new ResponseEntity<>(orderId, HttpStatus.OK);


    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("orderId") long orderId){

        OrderResponse orderResponse= orderService.getOrderDetails(orderId);

        return new ResponseEntity<>(orderResponse,HttpStatus.OK);

    }
}
