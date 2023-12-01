package com.sivellexfc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String productCode;
    private String sellerId;
    private String productName;
    private Category category;
    private int stock;
    private double price;

    private boolean hasDiscount;
    private int discount;

    public Product() {
        UUID productCode = UUID.randomUUID();
        this.productCode = productCode.toString();
    }
}
