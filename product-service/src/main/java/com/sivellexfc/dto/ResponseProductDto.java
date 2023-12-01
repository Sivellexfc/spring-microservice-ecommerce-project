package com.sivellexfc.dto;

import com.sivellexfc.entity.Category;

public record ResponseProductDto(
        String sellerId,
        String productName,
        Category category,
        int stock,
        double price,
        boolean hasDiscount,
        int discount
) {
}
