package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    void testFetchTypes(){

        Category category = categoryRepo.findById(1L).get();
        System.out.println(category.getName());
        for(Product p: category.getProducts()){
            System.out.println(p.getName());
        }
    }


    @Test
    @Transactional
    void getAllCategories(){

        List<Category> categories = categoryRepo.findAll();
        for(Category c: categories){
            List<Product> products = c.getProducts();
            for(Product p: products){
                System.out.println(p.getName() );
            }
        }
    }

}