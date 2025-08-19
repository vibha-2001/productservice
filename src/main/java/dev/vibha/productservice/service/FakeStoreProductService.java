package dev.vibha.productservice.service;

import dev.vibha.productservice.dtos.FakeStoreProductDTO;
import dev.vibha.productservice.dtos.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String specificProductURL = "https://fakestoreapi.com/products/{id}";
    //private String createProductRequestURL = "https://fakestoreapi.com/products";
    private String productRequestGetURL = "https://fakestoreapi.com/products";
    public GenericProductDTO createProduct(GenericProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(productRequestGetURL, product, GenericProductDTO.class);
        return response.getBody();
    }

    private GenericProductDTO converFakeStoreDTOintoGenericProduct(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO product = new GenericProductDTO();
        product.setId((fakeStoreProductDTO.getId()));
        product.setImage(fakeStoreProductDTO.getImage());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        return product;
    }
    public GenericProductDTO getProductById(long id){

       // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();    // Uses RestTemplate to call external API

        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductURL, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody(); //whatever was the response body convert it into fakestoreproductdto

        //responseEntity.getStatusCode();
        return converFakeStoreDTOintoGenericProduct(fakeStoreProductDTO);

    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO[]> response= restTemplate.getForEntity(productRequestGetURL, FakeStoreProductDTO[].class);

        List<GenericProductDTO> answer = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : Arrays.stream(response.getBody()).toList()){
            answer.add(converFakeStoreDTOintoGenericProduct(fakeStoreProductDTO));
        }

        return answer;
    }

    @Override
    public GenericProductDTO deleteProduct(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.execute(specificProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody(); //whatever was the response body convert it into fakestoreproductdto

        return converFakeStoreDTOintoGenericProduct(fakeStoreProductDTO);
    }
}
