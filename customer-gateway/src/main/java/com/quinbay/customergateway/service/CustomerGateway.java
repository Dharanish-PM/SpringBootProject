package com.quinbay.customergateway.service;

import com.quinbay.customergateway.data.Product;
import com.quinbay.customergateway.data.Products;
import com.quinbay.customergateway.data.PurchaseOrder;

import java.util.List;

public interface CustomerGateway  {

    List<Products> getAllProduct();
    String addProduct(Products product);
    String updateProduct(Product product);
    String deleteProduct(Long productId);
    String purchaseProduct(PurchaseOrder purchaseOrder);

    Product getProductById(Long productId);



}
