package com.quinbay.orderserviceP.controller;

import com.quinbay.orderserviceP.model.PurchaseOrder;
import com.quinbay.orderserviceP.serviceimplementation.OrderServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderService")
public class HttpMethodController {

    @Autowired
    OrderServiceImplementation orderServiceImplementation;

    @GetMapping("/getPurchaseOrders")
    public List<PurchaseOrder> getAllPurchaseOrders(){
        return orderServiceImplementation.getAllPurchaseOrders();

    }

    @GetMapping("/getCustomerOrder/{customerId}")
    public List<PurchaseOrder> getOrder(@PathVariable Long customerId){
        return orderServiceImplementation.getPurchaseOrder(customerId);
    }

    @PostMapping("/addPurchaseOrder")
    public String addPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
        return orderServiceImplementation.addPurchaseOrder(purchaseOrder);
    }

    @PostMapping("/purchaseProduct")
    public String purchaseProduct(@RequestBody PurchaseOrder purchaseOrder){
        return orderServiceImplementation.purchaseProduct(purchaseOrder);
    }




}
