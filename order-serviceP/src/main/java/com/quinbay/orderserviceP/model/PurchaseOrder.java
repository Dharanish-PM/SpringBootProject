package com.quinbay.orderserviceP.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="purchaseOrders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    private Long customerId;
    private Long productId;
    private int productQuantity;

}
