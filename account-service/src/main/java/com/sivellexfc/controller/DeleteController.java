package com.sivellexfc.controller;

import com.sivellexfc.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class DeleteController {

    private final AccountService accountService;

    public DeleteController(AccountService accountService) {
        this.accountService = accountService;
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAll(){
        return accountService.deleteAll();
    }

}
