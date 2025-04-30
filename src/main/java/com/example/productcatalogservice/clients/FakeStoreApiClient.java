package com.example.productcatalogservice.clients;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getProductById(Long productId){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{productId}", HttpMethod.GET, null,FakeStoreProductDto.class,productId);
        return validateResponse(fakeStoreProductDtoResponseEntity);
    }

    public FakeStoreProductDto updateProductById(Long productId, FakeStoreProductDto fakeStoreProductDto){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,fakeStoreProductDto, FakeStoreProductDto.class,productId);
        return validateResponse(fakeStoreProductDtoResponseEntity);
    }

    public List<FakeStoreProductDto> getAllProducts(){

        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoArrayResponseEntity = requestForEntity("https://fakestoreapi.com/products",HttpMethod.GET,null,FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtoArray = validateArrayResponse(fakeStoreProductDtoArrayResponseEntity);
        if(fakeStoreProductDtoArray == null){
            return null;
        }

        return Arrays.asList(fakeStoreProductDtoArray);
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products",HttpMethod.POST,fakeStoreProductDto, FakeStoreProductDto.class);
        return validateResponse(fakeStoreProductDtoResponseEntity);

    }

    public FakeStoreProductDto deleteProductById(Long productId){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE, null,FakeStoreProductDto.class,productId);
        return validateResponse(fakeStoreProductDtoResponseEntity);
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto validateResponse(ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity){
        if(fakeStoreProductDtoResponseEntity.getBody() == null || fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.valueOf(500))){
            return null;
        }

        return fakeStoreProductDtoResponseEntity.getBody();
    }

    private FakeStoreProductDto[] validateArrayResponse(ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity){
        if(fakeStoreProductDtoResponseEntity.getBody() == null || fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.valueOf(500))){
            return null;
        }

        return fakeStoreProductDtoResponseEntity.getBody();
    }
}
