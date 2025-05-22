package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.SortParam;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {

    Page<Product> search(String query, Integer pageSize, Integer pageNo,List<SortParam> sortParams);
}
