package com.sivellexfc.client;

import com.sivellexfc.dto.ResponseAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "account-service",path = "/api/account")
public interface AccountServiceClient {

    @GetMapping("/{accountId}")
    ResponseEntity<ResponseAccountDto> getAccount(@PathVariable String accountId);

    @GetMapping("/getAccountsByStoreNamePrefix")
    ResponseEntity<List<ResponseAccountDto>> getAccountsByStoreNamePrefix(@RequestParam(name = "storeNamePrefix") String storeNamePrefix);

    @GetMapping("/isExist")
    boolean isAccountExist(@RequestParam String accountId);

    @GetMapping("/isSeller")
    boolean isAccountSeller(@RequestParam String accountId);

    @GetMapping("/store")
    String getStoreIdByAccountId(@RequestParam(name = "accountId") String accountId);

    @GetMapping("/deneme")
    String denemeAc();
}
