package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testJpaMethods(){

        List<Product> products = productRepo.findProuctByPriceBetween(75000.00,250000.00);
        System.out.println(products);

        List<Product> primeProducts = productRepo.findProductByIsPrime(true);
        System.out.println(primeProducts.size());


        List<Product> orderProductsDesc = productRepo.findProductByOrderByPriceDesc();
        for (Product p : orderProductsDesc) {
            System.out.println(p.getPrice());
        }


        List<Product> orderProductsById = productRepo.findProductByOrderById();
        System.out.println(orderProductsById );

        String desc = productRepo.findProductDescriptionById(1);
        System.out.println(desc);

        String categoryName = productRepo.findProductCategoryNameById(1L);
        System.out.println(categoryName);

    }

}