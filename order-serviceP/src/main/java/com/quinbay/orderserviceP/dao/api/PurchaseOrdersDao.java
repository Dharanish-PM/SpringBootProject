package com.quinbay.orderserviceP.dao.api;

import com.quinbay.orderserviceP.model.PurchaseOrder;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.function.Function;

public interface PurchaseOrdersDao extends MongoRepository<PurchaseOrder,Long> {

    List<PurchaseOrder> findByCustomerId(Long customerId);

}
