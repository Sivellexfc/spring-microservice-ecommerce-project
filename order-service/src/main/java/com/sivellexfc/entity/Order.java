package com.sivellexfc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


}
