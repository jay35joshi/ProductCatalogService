package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long productId) {

        System.out.println("Reading from DB");
        Optional<Product> productOptional = productRepo.findById(productId);

        if(productOptional.isPresent()){
            return productOptional.get();
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
        System.out.println("Storing in DB");
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product inputProduct) {
        return null;
    }

    @Override
    public Product deleteProductById(Long productId) {
        return null;
    }
}
