package com.sivellexfc.dto;

import com.sivellexfc.entity.Category;

import java.util.UUID;

public record ProductDto(
        String productCode,
        String sellerId,
        String productName,
        Category category,
        int stock,
        double price,
        boolean hasDiscount,
        int discount
) {
}
