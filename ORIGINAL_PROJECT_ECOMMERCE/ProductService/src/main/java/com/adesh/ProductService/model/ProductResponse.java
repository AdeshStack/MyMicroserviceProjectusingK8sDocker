package com.adesh.ProductService.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private  String productName;
    private  long productId;
    private long quantity;
    private  long price;
}
