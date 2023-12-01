package com.sivellexfc.dto;

import org.springframework.data.annotation.Id;

public record RequestProductDto(
        String id,
        String productName,
        String productId,
        String sellerId,
        int stock,
        double price,
        boolean hasDiscount,
        int discount

) {
}

