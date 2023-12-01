package com.sivellexfc.repository;

import com.sivellexfc.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepository extends MongoRepository<Store,String> {

    Store findByStoreName(String storeName);
    List<Store> findStoreByStoreNameStartingWith(String prefixStoreName);
    List<Store> findStoreByStorePointIsBetween(double firstValue, double secondValue);
    Store findStoreByOwnerAccountId(String ownerAccountId);
}
