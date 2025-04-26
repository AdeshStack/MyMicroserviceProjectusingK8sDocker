package com.adesh.ProductService.controller;


import com.adesh.ProductService.model.ProductRequest;
import com.adesh.ProductService.model.ProductResponse;
import com.adesh.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct( @RequestBody ProductRequest productRequest){

        long productId= productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId){

       return new ResponseEntity<>( productService.getProduct(productId),HttpStatus.OK);

    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long id, @RequestParam long quantity){

        productService.reduceQuantity(id,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
