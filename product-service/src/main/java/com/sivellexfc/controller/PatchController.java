package com.sivellexfc.controller;


import com.sivellexfc.dto.ProductDto;
import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.dto.ResponseProductDto;
import com.sivellexfc.entity.Product;
import com.sivellexfc.service.ProductService;
import com.sivellexfc.wrapper.ProductWrapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class PatchController {

    private final ProductService productService;

    public PatchController(ProductService productService) {
        this.productService = productService;
    }

    @SneakyThrows
    @PatchMapping("/update/{productId}")
    public ResponseEntity<ResponseProductDto> editProduct(@PathVariable String productId,
                                                  @RequestBody RequestProductDto requestProductDto,
                                                  @RequestParam String accountId){
        return productService.updateProduct(requestProductDto,accountId,productId);
    }
}
