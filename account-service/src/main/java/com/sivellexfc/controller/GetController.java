package com.sivellexfc.controller;


import com.sivellexfc.client.StoreServiceClient;
import com.sivellexfc.model.Account;
import com.sivellexfc.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class GetController {

    private final AccountService accountService;
    private final StoreServiceClient storeServiceClient;

    public GetController(AccountService accountService, StoreServiceClient storeServiceClient) {
        this.accountService = accountService;
        this.storeServiceClient = storeServiceClient;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId){
        Optional<Account> account = accountService.getAccountById(accountId);
        if(account.isPresent()) return ResponseEntity.ok(account.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/getAccountsByStoreNamePrefix")
    public ResponseEntity<List<Account>> getAccountsByUsernamePrefix(@RequestParam String usernamePrefix){
        return ResponseEntity.ok(accountService.searchUsersByUsernamePrefix(usernamePrefix));
    }

    @GetMapping("/isExist")
    public boolean isAccountExist(@RequestParam(name = "accountId") String accountId){
        return accountService.isAccountExist(accountId);
    }

    @GetMapping("/isSeller")
    public boolean isAccountSeller(@RequestParam(name = "accountId") String accountId){
        return accountService.isAccountSeller(accountId);
    }

    @GetMapping("/store")
    public String getStoreIdByAccountId(@RequestParam(name = "accountId") String accountId){
        return accountService.getStoreId(accountId);
    }

    @GetMapping("/deneme")
    public String denemeAc(){
        return storeServiceClient.denemeAc();
    }

}
