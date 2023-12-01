package com.sivellexfc.wrapper;

import com.sivellexfc.dto.RequestStoreDto;
import com.sivellexfc.dto.RequestUpdateStoreDto;
import com.sivellexfc.dto.ResponseStoreDto;
import com.sivellexfc.model.Store;

public class ModelWrapper {

    public static ResponseStoreDto entityToResponseStoreDto(Store store){
        return new ResponseStoreDto(
                store.getId(),
                store.getOwnerAccountId(),
                store.getStoreName(),
                store.getInventoryList(),
                store.getStorePoint()
        );
    }

    public static Store requestStoreDtoToEntity(RequestStoreDto requestStoreDto){
        Store store = new Store();
        store.setOwnerAccountId(requestStoreDto.ownerAccountId());
        store.setStoreName(requestStoreDto.storeName());
        return store;
    }

    public static Store requestUpdateStoreDtoToEntity(RequestUpdateStoreDto requestUpdateStoreDto){
        Store store = new Store();
        store.setStorePoint(requestUpdateStoreDto.storePoint());
        store.setOwnerAccountId(requestUpdateStoreDto.ownerAccountId());
        store.setStoreName(requestUpdateStoreDto.storeName());
        store.setInventoryList(requestUpdateStoreDto.inventoryList());
        return store;
    }




}
