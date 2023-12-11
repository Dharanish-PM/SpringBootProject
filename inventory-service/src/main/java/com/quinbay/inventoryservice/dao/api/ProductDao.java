package com.quinbay.inventoryservice.dao.api;

import com.quinbay.inventoryservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {
    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    Product findByName(String productName);




//
//    List<Product> findByCategory(int category_id);

}
