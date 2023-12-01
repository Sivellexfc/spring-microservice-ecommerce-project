package com.sivellexfc.service;

import com.sivellexfc.client.AccountServiceClient;
import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.dto.RequestStoreDto;
import com.sivellexfc.dto.ResponseStoreDto;
import com.sivellexfc.model.Store;
import com.sivellexfc.repository.StoreRepository;
import com.sivellexfc.wrapper.ModelWrapper;
import jakarta.ws.rs.NotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final AccountServiceClient accountServiceClient;

    public StoreService(StoreRepository storeRepository, AccountServiceClient accountServiceClient) {
        this.storeRepository = storeRepository;
        this.accountServiceClient = accountServiceClient;
    }

    public ResponseEntity deleteAll() {
        storeRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }

    public ResponseEntity<ResponseStoreDto> createStore(String storeName, String accountId){
        try{
            if(accountServiceClient.isAccountExist(accountId)){
                if(accountServiceClient.isAccountSeller(accountId)){
                    Store store = new Store();
                    store.setStoreName(storeName);
                    store.setOwnerAccountId(accountId);
                    return ResponseEntity.ok(ModelWrapper.entityToResponseStoreDto(storeRepository.save(store)));
                }
                else throw new NotAllowedException("You do not have permission");
            }
            else throw new AccountNotFoundException("Account is not found");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (NotAllowedException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }

    }

    public void deleteStoreByStoreId(String storeId){
        storeRepository.deleteById(storeId);
    }

    public Store updateStore(Store store){
        return storeRepository.save(store);
    }

    public Optional<Store> getStoreByStoreId(String storeId){
        return storeRepository.findById(storeId);
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public Store getStoreByStoreName(String storeName){ return storeRepository.findByStoreName(storeName);}

    public List<Store> getStoresByStoreNameStartingWith(String prefixStoreName){
        return storeRepository.findStoreByStoreNameStartingWith(prefixStoreName);
    }

    public List<Store> getStoresByPointRange(double firstValue ,double secondValue){
        return storeRepository.findStoreByStorePointIsBetween(firstValue,secondValue);
    }

    public Store getStoreByOwnerAccountId(String ownerAccountId){
        return storeRepository.findStoreByOwnerAccountId(ownerAccountId);
    }


    public ResponseEntity addProduct(String productId, String storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        System.out.println("storeclient service");
        if(store.isPresent()){
            Store updatedStore = store.get();
            List<String> productList = updatedStore.getInventoryList();
            productList.add(productId);
            updatedStore.setInventoryList(productList);
            storeRepository.save(updatedStore);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
