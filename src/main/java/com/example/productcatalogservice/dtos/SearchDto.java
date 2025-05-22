package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchDto {

    private String query;
    private int pageSize;
    private int pageNumber;
    private List<SortParam> sortParams = new ArrayList<>();


}
