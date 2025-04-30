package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private CategoryDto category;
}
