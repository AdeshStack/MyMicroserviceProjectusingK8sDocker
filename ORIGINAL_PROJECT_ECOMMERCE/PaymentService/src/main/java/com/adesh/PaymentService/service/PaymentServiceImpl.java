package com.adesh.PaymentService.service;


import com.adesh.PaymentService.entity.TransactionDetails;
import com.adesh.PaymentService.model.PaymentMode;
import com.adesh.PaymentService.model.PaymentRequest;
import com.adesh.PaymentService.model.PaymentResponse;
import com.adesh.PaymentService.repositories.TransactionDetailRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailRepo detailRepo;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details {} ",paymentRequest);

        TransactionDetails details=TransactionDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentDate(Instant.now())
                .paymentStatus("Success")
                .paymentMode(paymentRequest.getPaymentMode().name())
                .amount(paymentRequest.getAmount())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .build();

       Long id= this.detailRepo.save(details).getId();
       log.info("Transaction completed for this id {}",id);
       return id;
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
       TransactionDetails transactionDetails = this.detailRepo.findByOrderId(orderId);

   PaymentResponse paymentResponse=PaymentResponse.builder()
               .paymentId(transactionDetails.getId())
               .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
               .orderId(transactionDetails.getOrderId())
               .status(transactionDetails.getPaymentStatus())
               .paymentDate(Instant.now())
               .amount(transactionDetails.getAmount())
               .build();

   return paymentResponse;
    }
}
