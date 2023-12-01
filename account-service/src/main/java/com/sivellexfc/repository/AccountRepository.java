package com.sivellexfc.repository;

import com.sivellexfc.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account,String> {

    @Query("{'storeName': {'$regex': '^?0', '$options': 'i'}}")
    List<Account> findAccountByStoreNameStartingWith(String prefix);
}
