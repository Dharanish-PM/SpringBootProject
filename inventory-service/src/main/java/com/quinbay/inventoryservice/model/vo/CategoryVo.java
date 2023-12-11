package com.quinbay.inventoryservice.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quinbay.inventoryservice.model.entity.Category;
import com.quinbay.inventoryservice.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryVo {

        private Long id;
    private String name;
    private List<Product> products;

}
