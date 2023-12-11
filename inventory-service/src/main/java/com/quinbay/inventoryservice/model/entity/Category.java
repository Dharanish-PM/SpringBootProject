package com.quinbay.inventoryservice.model.entity;

import com.quinbay.inventoryservice.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name=FieldNames.CATEGORY_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name=FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = FieldNames.NAME)
    private String name;

    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name="category_product_foreign_key",referencedColumnName = "id")
    private List<Product> products;

}
