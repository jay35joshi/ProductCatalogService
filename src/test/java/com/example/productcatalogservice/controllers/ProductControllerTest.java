package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    //It will check for corresponding bean and try to create mock around it based on @primary or @Qualifier annotation
    @MockBean
    @Qualifier("sps")
    private IProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void TestGetProductById_WithValidId_RunSuccessfully() {

        //Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test");

        when(productService.getProductById(productId)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity=  productController.getProductById(productId);

        //assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(productId, productDtoResponseEntity.getBody().getId());
        assertEquals("Test", productDtoResponseEntity.getBody().getName());

        //how many times interaction happening with product service
        //whenever you need to do anything on your mock just use verify method
        //any kind of validation on mock
        //This validates that interaction with ps is only one time when getproductbyid is called
        verify(productService,times(1)).getProductById(productId);

    }

    @Test
    public void TestGetProductById_WithZeroId_ThrowsIllegalArgumentException() {

        //act and assert
        //assert on final exception you are throwing from exception block
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> productController.getProductById(0L));
        assertEquals("Product with id 0 not accessible", ex.getMessage());

    }

    //Considering a scenario where exception is thrown from downstream
    @Test
    public void TestGetProductById_WhenProductServiceThrowsException_ThrowsSameException() {
        //arrange
        when(productService.getProductById(any(Long.class))).thenThrow(new RuntimeException("Something went bad"));

        //act and assert
        assertThrows(RuntimeException.class, () -> productController.getProductById(101L));
    }

    //Argument captures
    @Test
    public void TestGetProductById_ProductServiceCalledWithCorrectArguments_RunSuccessfully() {

        //Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test");

        when(productService.getProductById(productId)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity=  productController.getProductById(productId);

        //assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(productId, idCaptor.getValue());

    }

}