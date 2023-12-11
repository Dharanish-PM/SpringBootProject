package com.quinbay.orderserviceP.serviceimplementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.orderserviceP.dao.api.PurchaseOrdersDao;
import com.quinbay.orderserviceP.model.PurchaseOrder;
import com.quinbay.orderserviceP.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("PurchaseOrder")
public class OrderServiceImplementation implements OrderService {
    @Autowired
    RestTemplate restTemplate;

    private String INVENTORY_URL="http://localhost:9010/inventoryService";

    @Autowired
    PurchaseOrdersDao purchaseOrdersDao;

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrdersDao.findAll();

    }

    @Override
    public String addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrdersDao.save(purchaseOrder);
        return "Sucessful";
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrder(Long customerId) {
        ObjectMapper objectMapper=new ObjectMapper();

        List<PurchaseOrder> result = objectMapper.convertValue(purchaseOrdersDao.findByCustomerId(customerId), List.class);
        return result;
    }

    @Override
    public String purchaseProduct(PurchaseOrder purchaseOrder) {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PurchaseOrder> entity=new HttpEntity<>(purchaseOrder,headers);
        System.out.println(purchaseOrder.getProductQuantity());
        purchaseOrdersDao.save(purchaseOrder);

        String orderResponse= restTemplate.exchange(INVENTORY_URL+"/purchaseProduct", HttpMethod.POST, entity , String.class ).getBody();
        if(orderResponse.equals("Item Available")) {
            return "Order placed...";
        }
        return "Not available";

    }
}
