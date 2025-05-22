package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


    Optional<Product> findById(Long productId);

    Product save(Product product);

    //product between prices(range of prices)
    List<Product> findProuctByPriceBetween(Double lower, Double higher);

    //find all products which are prime
    List<Product> findProductByIsPrime(Boolean isPrime);

    //order by price -- This is wrong got exception Human error
    //List<Product> findProductOrderByPrice();

    List<Product> findProductByOrderByPriceDesc();

    List<Product> findProductByOrderById();

    //pass Id and get product description -- Not working JPA can't able to understand -- to resolve use @Query
    @Query("SELECT p.description from Product p where p.id=?1")
    String findProductDescriptionById(long id);

    //Category name from product id
    @Query("SELECT c.name from Product p join Category  c on p.category.id=c.id where p.id=:id")
    String findProductCategoryNameById(Long id);

    Page<Product> findByName(String name, Pageable pageable);
}
