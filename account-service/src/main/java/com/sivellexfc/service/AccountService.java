package com.sivellexfc.service;

import com.sivellexfc.client.StoreServiceClient;
import com.sivellexfc.model.Account;
import com.sivellexfc.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final StoreServiceClient storeServiceClient;

    public AccountService(AccountRepository accountRepository, StoreServiceClient storeServiceClient) {
        this.accountRepository = accountRepository;
        this.storeServiceClient = storeServiceClient;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(String accountId){
        return accountRepository.findById(accountId);
    }

    public List<Account> searchUsersByUsernamePrefix(String prefix){
        return accountRepository.findAccountByStoreNameStartingWith(prefix);
    }

    public boolean isAccountExist(String accountId){
        return accountRepository.existsById(accountId);
    }

    public boolean isAccountSeller(String accountId) {
        if(isAccountExist(accountId)) return accountRepository.findById(accountId).get().isSeller();
        return false;
    }

    public String getStoreId(String accountId){
        return accountRepository.findById(accountId).get().getStoreId();
    }

    public Account createBusinessAccount(Account account,String storeName){

        account.setStoreId(storeServiceClient.createStore(storeName, account.getId()).getBody().id());
        return accountRepository.save(account);
    }

    public ResponseEntity deleteAll() {
        accountRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }
}
