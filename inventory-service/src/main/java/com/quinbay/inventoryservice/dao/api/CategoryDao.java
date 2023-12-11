package com.quinbay.inventoryservice.dao.api;

import com.quinbay.inventoryservice.model.entity.Category;
import com.quinbay.inventoryservice.model.entity.Product;
import com.quinbay.inventoryservice.model.vo.CategoryVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Long> {

    @Override
    List<Category> findAll();

    @Override
    Category save(Category product);

//    @Query("Select new com.quinbay.inventoryservice.model.vo.CategoryVo(c.name,p.name) FROM Category c JOIN c.products p")
//    List<CategoryVo> getCategoryWithProducts();
}
