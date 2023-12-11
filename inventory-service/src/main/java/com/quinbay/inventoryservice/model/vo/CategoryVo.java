package com.quinbay.inventoryservice.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quinbay.inventoryservice.model.entity.Category;
import com.quinbay.inventoryservice.model.entity.Product;

import java.util.List;


public class CategoryVo {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private Long id;
    private String name;
    private List<Product> products;

}
