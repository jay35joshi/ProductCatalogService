package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("sps")
    private IProductService productService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void TestGetAllProducts_RunsSuccessfully() throws Exception {
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    public void TestGetAllProducts_ReceivesProductList() throws Exception {
        //Arrange
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("iphone");
       // product1.setPrime(false);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Airpods");
       // product2.setPrime(false);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productService.getAllProducts()).thenReturn(productList);

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : productList) {
            productDtos.add(from(product));
        }
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(productDtos)));
    }


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
