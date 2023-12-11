package com.quinbay.inventoryservice.controller;


import com.quinbay.inventoryservice.data.PurchaseOrder;
import com.quinbay.inventoryservice.model.entity.Category;
import com.quinbay.inventoryservice.model.entity.Product;
import com.quinbay.inventoryservice.model.vo.CategoryVo;
import com.quinbay.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quinbay.inventoryservice.model.vo.ProductVo;

import java.util.List;

@RestController
@RequestMapping("/inventoryService")
public class HttpMethodController {

    @Autowired
    InventoryService inventoryService;


    @GetMapping("/getAllProducts")
    @Cacheable(value = "products")
    public List<CategoryVo> getAllProducts(){
        return inventoryService.getAllProducts();
    }

    @GetMapping("/getProductById/{productId}")
    public ProductVo getProductById(@PathVariable Long productId){

        return inventoryService.getProductById(productId);
    }

    @GetMapping("/getProductByName/{productName}")
    public ProductVo getProductByName(@PathVariable String productName){

        return inventoryService.getProductByName(productName);
    }

    @PostMapping("/addProduct")
    public String addProductByCategory(@RequestBody CategoryVo categoryVo){
        return inventoryService.addProduct(categoryVo);
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestBody ProductVo productVo){
        return inventoryService.updateProduct(productVo);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    @CacheEvict(value = "products",allEntries = true)
    public String deleteProduct(@PathVariable Long productId){
        return inventoryService.deleteProduct(productId);
    }



    @PostMapping("/purchaseProduct")
    public String purchaseProduct(@RequestBody PurchaseOrder purchaseOrder){
        return inventoryService.purchaseProduct(purchaseOrder);
    }

//    @GetMapping("/getByCategory/{productId}")
//
//    public CategoryVo getProductById(@PathVariable Long productId){
//        return inventoryService.getProductById(productId);
//    }

//    @GetMapping("/allProducts")
//    public ResponseEntity<List<ProductVo>> getallProducts(){
//        return inventoryService.getallProducts();
//    }

//    @PostMapping("/addProduct")
//    public ResponseEntity<String> addProduct(@RequestBody ProductVo productVo){
//        return inventoryService.addProduct(productVo);
//    }
//
//    //Not implemented fully
//    @GetMapping("/getProduct/{category}")
//    public ResponseEntity<List<ProductVo>> getProductByCategory(@PathVariable int category){
//        return inventoryService.getProductByCategory(category);
//    }

    //with many to one









}
