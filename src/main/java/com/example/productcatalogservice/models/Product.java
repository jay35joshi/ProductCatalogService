package com.example.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    @ManyToOne
    @Cascade(CascadeType.ALL) // if product is added category is compulsory but vice versa is not true
    private Category category;
    private boolean isPrime;
}
