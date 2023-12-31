package com.quinbay.customergateway.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Products {

    @JsonProperty("id")
    private Long id;

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

    @JsonProperty("name")
    private String name;
    @JsonProperty("products")
    private List<Product> products;

}
