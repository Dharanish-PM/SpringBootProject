package com.quinbay.inventoryservice.model.vo;


import com.quinbay.inventoryservice.model.entity.Category;
import lombok.Data;


@Data
public class ProductVo {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private Category category;

}
