package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.dtos.SearchDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProduct(@RequestBody SearchDto searchDto){
//To do page product to page product dto
       // List<ProductDto> results = new ArrayList<>();
        return searchService.search(searchDto.getQuery(),searchDto.getPageSize(),searchDto.getPageNumber(),searchDto.getSortParams());
//          for (Product product : products) {
//               results.add(from(product));
//          }

          //return results;
    }

//    {
//        "query":"JAY",
//            "pageSize": 3,
//            "pageNumber": 0,
//            "sortParams" : [
//        {
//            "sortType" : "ASC",
//                "sortCriteria" : "price"
//        },
//        {
//            "sortType" : "DESC",
//                "sortCriteria" : "id"
//        }
//    ]
//    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto productCategoryDto = new CategoryDto();
            productCategoryDto.setId(product.getCategory().getId());
            productCategoryDto.setName(product.getCategory().getName());
            productCategoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(productCategoryDto);
        }

        return productDto;

    }
}