package com.sivellexfc.dto;

import com.sivellexfc.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class RequestProductDto{
    private String sellerId;
    private String productName;
    private Category category;
    private int stock;
    private double price;
    private boolean hasDiscount;
    private int discount;
}
