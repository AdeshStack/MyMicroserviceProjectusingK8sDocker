package com.adesh.Order_Service.external.client;


import com.adesh.Order_Service.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name="PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduceQuantity/{id}")
     ResponseEntity<Void> reduceQuantity(@PathVariable long id, @RequestParam long quantity);
    default void fallback(Exception e){
        throw new CustomException("Product Service is not available ",
                "UNAVAILABLE",
                500);
    }
}

