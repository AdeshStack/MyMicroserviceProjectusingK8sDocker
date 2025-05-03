package com.adesh.Order_Service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;

    private ProductDetail productDetail;
    private PaymentDetails paymentDetails;


    @Data
    @Builder
    public static class ProductDetail {

        private  String productName;
        private  long productId;
        private long quantity;
        private  long price;
    }
    @Data
    @Builder
    public static  class PaymentDetails{
        private long paymentId;
        private PaymentMode paymentMode;
        private String paymentStatus;
        private Instant paymentDate;
    }

}