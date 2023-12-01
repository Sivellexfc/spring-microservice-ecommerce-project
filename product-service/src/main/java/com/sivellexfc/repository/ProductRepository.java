package com.sivellexfc.repository;


import com.sivellexfc.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,Long> {
    List<Product> findBySellerId(long sellerId);
    Optional<Product> findById(String id);
}
