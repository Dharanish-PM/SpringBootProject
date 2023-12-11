package com.quinbay.inventoryservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quinbay.inventoryservice.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name=FieldNames.PRODUCT_T)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product implements Serializable {

    @Id
    @Column(name = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name=FieldNames.NAME)
    private String name;

    @Column(name = FieldNames.PRICE)
    private double price;

    @Column(name = FieldNames.QUANTITY)
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = FieldNames.CATEGORY_ID)
    private Category category;

}