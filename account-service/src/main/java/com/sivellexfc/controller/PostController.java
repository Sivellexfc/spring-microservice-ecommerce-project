package com.sivellexfc.controller;

import com.sivellexfc.model.Account;
import com.sivellexfc.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class PostController {

    private final AccountService accountService;

    public PostController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create-personal")
    public ResponseEntity<Account> createPersonalAccount(@RequestBody Account account){
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PostMapping("/create-business")
    public ResponseEntity<Account> createBusinessAccount(@RequestBody Account account,
                                                         @RequestParam String storeName){
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(accountService.createBusinessAccount(createdAccount,storeName));
    }
}
