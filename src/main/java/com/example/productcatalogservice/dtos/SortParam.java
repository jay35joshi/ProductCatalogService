package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {

    private SortType sortType;
    private String sortCriteria;
}
