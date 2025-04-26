package com.adesh.ProductService.service;


import com.adesh.ProductService.entity.Product;
import com.adesh.ProductService.exception.ProductServiceCustomException;
import com.adesh.ProductService.model.ProductRequest;
import com.adesh.ProductService.model.ProductResponse;
import com.adesh.ProductService.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements  ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product...");
        Product product= Product.builder().productName(productRequest.getName()).price(productRequest.getPrice()).quantity(productRequest.getQuantity()).build();

        Product p=this.productRepository.save(product);
        log.info(p.getProductName());

        return product.getProductId();

    }

    @Override
    public ProductResponse getProduct(Long productId) {

        Product product= this.productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product with given id  not found","PRODUCT_NOT_FOUND"));

        return ProductResponse.builder()
                .productName(product.getProductName())
                .productId(product.getProductId())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

    @Override
    public void reduceQuantity(long id, long quantity) {
        log.info("Reduce quantity {} and  {}",id,quantity);

        Product product= this.productRepository.findById(id).orElseThrow(()-> new ProductServiceCustomException("Product with given id  not found","PRODUCT_NOT_FOUND"));


        if(product.getQuantity()<quantity){
            throw  new ProductServiceCustomException("Product doesn't  have sufficient quantity","INSUFFICIENT_QUANTITY");
        }
        long currentQuantity=product.getQuantity()-quantity;

        product.setQuantity(currentQuantity);
        this.productRepository.save(product);
        log.info("product quantity updated successfully");

    }
}
