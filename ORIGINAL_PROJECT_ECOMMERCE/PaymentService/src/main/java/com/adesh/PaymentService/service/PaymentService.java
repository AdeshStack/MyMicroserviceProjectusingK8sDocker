package com.adesh.PaymentService.service;

import com.adesh.PaymentService.model.PaymentRequest;
import com.adesh.PaymentService.model.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
