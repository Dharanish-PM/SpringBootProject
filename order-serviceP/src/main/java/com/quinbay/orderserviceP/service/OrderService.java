package com.quinbay.orderserviceP.service;

import com.quinbay.orderserviceP.model.PurchaseOrder;

import java.util.List;


public interface OrderService {
    List<PurchaseOrder> getAllPurchaseOrders();
    String addPurchaseOrder(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> getPurchaseOrder(Long cutstomerId);
    String purchaseProduct(PurchaseOrder purchaseOrder);

}
