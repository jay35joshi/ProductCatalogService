package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("sps")
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        if (products == null) {
            return null;
        }

        for (Product product : products) {
            productDtos.add(from(product));
        }

        return productDtos;

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {

            if(productId < 0){
                throw new IllegalArgumentException("Product is invalid");
            }
            else if(productId == 0){
                throw new IllegalArgumentException("Product with id 0 not accessible");
            }
            //Added to understand argmentcaptor
            productId--;
            Product product = productService.getProductById(productId);
            MultiValueMap headers = new LinkedMultiValueMap();
            if (product == null) {
                headers.add("error", "Product not found");
                return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ProductDto productDto = from(product);
            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable("id") Long productId) {
        if(productId <= 0){
            throw new IllegalArgumentException("Product is invalid");
        }
        Product deletedProduct = productService.deleteProductById(productId);
        MultiValueMap headers = new LinkedMultiValueMap();
        if (deletedProduct == null) {
            headers.add("error", "Product not found");
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ProductDto productDto = from(deletedProduct);
        return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return from(productService.createProduct(from(productDto)));

    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {

        Product inputProduct = from(productDto);

        Product outputProduct = productService.updateProduct(id, inputProduct);

        if(outputProduct == null){
            throw new IllegalArgumentException("Product not found");
        }

        return from(outputProduct);


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

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }

        return product;
    }






}
