package com.sivellexfc.controller;

import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.dto.RequestStoreDto;
import com.sivellexfc.dto.RequestUpdateStoreDto;
import com.sivellexfc.dto.ResponseStoreDto;
import com.sivellexfc.model.Store;
import com.sivellexfc.service.StoreService;
import com.sivellexfc.wrapper.ModelWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class PostController {

    private final StoreService storeService;

    public PostController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseStoreDto> createStore(@RequestParam String storeName,
                                                        @RequestParam String accountId){
        System.out.println(storeName);
        System.out.println(accountId);
        return storeService.createStore(storeName,accountId);
    }

    @PostMapping("/update/{storeId}")
    public ResponseEntity<ResponseStoreDto> updateStore(@PathVariable String storeId,
                                                        @RequestBody RequestUpdateStoreDto requestUpdateStoreDto){
        try{
            Optional<Store> existingStore = storeService.getStoreByStoreId(storeId);
            if(existingStore.isPresent()){
                Store updatedStore = ModelWrapper.requestUpdateStoreDtoToEntity(requestUpdateStoreDto);
                return ResponseEntity.ok(ModelWrapper.entityToResponseStoreDto(storeService.updateStore(updatedStore)));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity addProduct(@RequestParam String productId,
                                     @RequestParam String storeId){
        return storeService.addProduct(productId, storeId);

    }



}








