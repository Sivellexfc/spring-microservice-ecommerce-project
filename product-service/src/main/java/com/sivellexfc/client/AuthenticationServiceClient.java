package com.sivellexfc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "AUTHENTICATION-SERVICE")
public interface AuthenticationServiceClient {

    @GetMapping("/getUserIdByAuthHeader")
    String getAccountId(@RequestParam("authHeader") String authHeader);
}
