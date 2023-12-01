package com.sivellexfc.controller;

import com.sivellexfc.client.AccountServiceClient;
import com.sivellexfc.entity.Product;
import com.sivellexfc.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class GetController {

    private final ProductService productService;
    private final AccountServiceClient accountServiceClient;

    public GetController(ProductService productService, AccountServiceClient accountServiceClient) {
        this.productService = productService;
        this.accountServiceClient = accountServiceClient;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/get/{sellerId}")
    public ResponseEntity<List<Product>> getProductBySeller(@PathVariable long sellerId){
        return ResponseEntity.ok(productService.getProductsBySellerId(sellerId));
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId){
        if(productService.getProductByProductId(productId).isPresent())
            return ResponseEntity.ok(productService.getProductByProductId(productId).get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/test")
    public String deneme(){
        return "test message";
    }

//    @GetMapping("/deneme")
//    public String deneme(){
//        return accountServiceClient.denemeAc();
//    }
}
