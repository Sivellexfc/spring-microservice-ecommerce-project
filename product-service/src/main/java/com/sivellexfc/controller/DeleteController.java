package com.sivellexfc.controller;

import com.sivellexfc.entity.Product;
import com.sivellexfc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class DeleteController {

    private final ProductService productService;

    public DeleteController(ProductService productService) {
        this.productService = productService;
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable long productId){
        productService.deleteProductByProductId(productId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAll(){
        return productService.deleteAll();
    }

}
