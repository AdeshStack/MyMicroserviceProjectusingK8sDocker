package com.adesh.Order_Service.service;


import com.adesh.Order_Service.entity.Order;
import com.adesh.Order_Service.exception.CustomException;
import com.adesh.Order_Service.external.client.PaymentService;
import com.adesh.Order_Service.external.client.ProductService;
import com.adesh.Order_Service.external.reponse.PaymentResponse;
import com.adesh.Order_Service.external.request.PaymentRequest;
import com.adesh.Order_Service.model.OrderRequest;
import com.adesh.Order_Service.model.OrderResponse;
import com.adesh.Order_Service.model.ProductResponse;
import com.adesh.Order_Service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository repo;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;


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

        PaymentRequest paymentRequest= PaymentRequest.builder()
                .orderId(orderId)
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus=null;
        try{
            //calling payment service via feign client.
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully");
            orderStatus="Placed";
        }catch (Exception e){

            log.info("Error occurred in payment");
        }

        order.setOrderStatus(orderStatus);
        this.repo.save(order);

        log.info("calling payment service to complete the payment ");




        log.info("Order places successfully with Order Id: {}",orderId);
        return orderId;


    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {


      Order order=this.repo.findById(orderId).orElseThrow(()->new CustomException("Order not found with this id"+orderId,"NOT_FOUND",404));

      log.info("Fetching the product service using orderid"+orderId);


      ProductResponse productResponse=this.restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class);

      log.info("Getting payment information from the payment service");

      PaymentResponse paymentResponse= restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getId(),
              PaymentResponse.class);

        OrderResponse.ProductDetail productDetail=OrderResponse.ProductDetail.builder()
              .productName(productResponse.getProductName())
              .productId(productResponse.getProductId())
              .build();

        OrderResponse.PaymentDetails paymentDetails=
                OrderResponse.PaymentDetails.builder()
                        .paymentId(paymentResponse.getPaymentId())
                        .paymentStatus(paymentResponse.getStatus())
                        .paymentDate(paymentResponse.getPaymentDate())
                        .paymentMode(paymentResponse.getPaymentMode())
                        .build();


      return OrderResponse.builder()
              .orderId(order.getId())
              .orderDate(Instant.now())
              .orderStatus(order.getOrderStatus())
              .paymentDetails(paymentDetails)
              .productDetail(productDetail)
              .amount(order.getAmount())
              .build();
    }
}
