package com.sivellexfc.wrapper;

import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.dto.ResponseProductDto;
import com.sivellexfc.entity.Product;

public class ProductWrapper {
    public static ResponseProductDto toResponseProductDto(Product product) {
        return new ResponseProductDto(
                product.getSellerId(),
                product.getProductName(),
                product.getCategory(),
                product.getStock(),
                product.getPrice(),
                product.isHasDiscount(),
                product.getDiscount()
        );
    }

    public static Product toEntity(RequestProductDto requestProductDto) {
        Product product = new Product();


        product.setProductName(requestProductDto.getProductName());
        product.setCategory(requestProductDto.getCategory());
        product.setDiscount(requestProductDto.getDiscount());
        product.setHasDiscount(requestProductDto.isHasDiscount());
        product.setPrice(requestProductDto.getPrice());
        product.setSellerId(requestProductDto.getSellerId());
        product.setStock(requestProductDto.getStock());
        return product;
    }

    public static Product toEntity(RequestProductDto requestProductDto,String productId) {
        Product product = new Product();
        product.setId(productId);

        product.setProductName(requestProductDto.getProductName());
        product.setCategory(requestProductDto.getCategory());
        product.setDiscount(requestProductDto.getDiscount());
        product.setHasDiscount(requestProductDto.isHasDiscount());
        product.setPrice(requestProductDto.getPrice());
        product.setSellerId(requestProductDto.getSellerId());
        product.setStock(requestProductDto.getStock());
        return product;
    }

}
