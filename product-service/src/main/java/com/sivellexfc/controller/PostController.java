package com.sivellexfc.controller;

import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.entity.Product;
import com.sivellexfc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/product")
@RestController
public class PostController {

    private final ProductService productService;

    public PostController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody RequestProductDto requestProductDto,
                                              @RequestParam String accountId){
        return productService.createProduct(requestProductDto,accountId);
    }



}
