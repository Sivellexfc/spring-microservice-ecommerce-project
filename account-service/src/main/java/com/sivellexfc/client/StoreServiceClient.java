package com.sivellexfc.client;

import com.sivellexfc.dto.RequestStoreDto;
import com.sivellexfc.dto.ResponseStoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "store-service",path = "/api/store")
public interface StoreServiceClient {

    @PostMapping("/create")
    ResponseEntity<ResponseStoreDto> createStore(@RequestParam String storeName,
                                                 @RequestParam String accountId);

    @GetMapping("/deneme")
    String denemeAc();
}
