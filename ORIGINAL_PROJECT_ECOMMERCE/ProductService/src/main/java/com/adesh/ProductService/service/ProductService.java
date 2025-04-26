package com.adesh.ProductService.service;

import com.adesh.ProductService.model.ProductRequest;
import com.adesh.ProductService.model.ProductResponse;

public  interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProduct(Long productId);

    void reduceQuantity(long id, long quantity);
}
