package com.quinbay.customergateway.data;

import lombok.Data;

@Data
public class PurchaseOrder {

    private Long customerId;
    private Long productId;
    private int productQuantity;

}
