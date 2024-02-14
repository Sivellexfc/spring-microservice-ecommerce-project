package com.sivellexfc.controller;


import com.sivellexfc.client.AccountServiceClient;
import com.sivellexfc.client.AuthenticationServiceClient;
import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.dto.ResponseProductDto;
import com.sivellexfc.service.ProductService;
import com.sivellexfc.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/product")
public class PatchController {

    private final ProductService productService;
    private final RequestService requestService;
    private final AccountServiceClient accountServiceClient;
    private final RestTemplate template;
    private final AuthenticationServiceClient authenticationServiceClient;

    public PatchController(ProductService productService, RequestService requestService, AccountServiceClient accountServiceClient, RestTemplate template, AuthenticationServiceClient authenticationServiceClient) {
        this.productService = productService;
        this.requestService = requestService;
        this.accountServiceClient = accountServiceClient;
        this.template = template;
        this.authenticationServiceClient = authenticationServiceClient;
    }

    @SneakyThrows
    @PatchMapping("/update")
    public ResponseEntity<ResponseProductDto> editProduct(@RequestParam String productId,
                                                  @RequestBody RequestProductDto requestProductDto,
                                                  @RequestParam String accountId){
        return productService.updateProduct(requestProductDto,accountId,productId);
    }

    @PatchMapping("/update/test")
    public String updateTest(HttpServletRequest request){
        return requestService.getUserIdFromAuthenticationService(request);
    }

}
