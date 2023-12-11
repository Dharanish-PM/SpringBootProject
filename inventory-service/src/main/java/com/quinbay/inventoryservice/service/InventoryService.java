package com.quinbay.inventoryservice.service;

import com.quinbay.inventoryservice.data.PurchaseOrder;
import com.quinbay.inventoryservice.model.vo.CategoryVo;
import com.quinbay.inventoryservice.model.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {

//    ResponseEntity<List<ProductVo>> getallProducts();
//    ResponseEntity<String> addProduct(ProductVo productVo);
//
//    ResponseEntity<List<ProductVo>> getProductByCategory(int category);

    String addProduct(CategoryVo categoryVo);
    List<CategoryVo> getAllProducts();

    String updateProduct(ProductVo productVo);

    String deleteProduct(Long productId);
    ProductVo getProductByName(String productName);

    String purchaseProduct(PurchaseOrder purchaseOrder);


}
