package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) //always have fields which are non null
public class ProductDto {

    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private CategoryDto category;
}
