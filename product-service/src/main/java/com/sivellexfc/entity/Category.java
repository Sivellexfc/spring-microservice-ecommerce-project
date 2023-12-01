package com.sivellexfc.entity;

public enum Category {

    PC("Bilgisayar"),GIYIM("Giyim"),GIDA("Gıda"),BOOK("Kitap")
    ;

    private String category;

    Category(String category) {
        this.category = category;
    }
}
