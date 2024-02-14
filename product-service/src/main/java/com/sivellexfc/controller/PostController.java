package com.sivellexfc.controller;

import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.entity.Product;
import com.sivellexfc.service.ProductService;
import com.sivellexfc.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/product")
@RestController
public class PostController {

    private final ProductService productService;
    private final RequestService requestService;

    public PostController(ProductService productService, RequestService requestService) {
        this.productService = productService;
        this.requestService = requestService;
    }

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody RequestProductDto requestProductDto,
                                              @RequestParam String accountId,
                                              HttpServletRequest request){
        String sellerId = requestService.getUserIdFromAuthenticationService(request);
        return productService.createProduct(requestProductDto,sellerId);
    }



}
