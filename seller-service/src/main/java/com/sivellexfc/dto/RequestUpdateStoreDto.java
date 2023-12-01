package com.sivellexfc.dto;

import java.util.List;

public record RequestUpdateStoreDto(
        String ownerAccountId,
        String storeName,
        List<String> inventoryList,
        double storePoint
) {

    public RequestUpdateStoreDto(
                            String ownerAccountId,
                            String storeName,
                            List<String> inventoryList,
                            double storePoint) {

        this.ownerAccountId = ownerAccountId;
        this.storeName = storeName;
        this.inventoryList = inventoryList;
        this.storePoint = storePoint;
    }
}