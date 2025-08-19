package dev.vibha.productservice.service;

import dev.vibha.productservice.dtos.FakeStoreProductDTO;
import dev.vibha.productservice.dtos.GenericProductDTO;
import dev.vibha.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    //private String createProductRequestURL = "https://fakestoreapi.com/products";
    private String productRequestGetURL = "https://fakestoreapi.com/products";
//    public GenericProductDTO createProduct(GenericProductDTO product){
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(createProductRequestURL, product, GenericProductDTO.class);
//        return response.getBody();
//    }

    public GenericProductDTO getProductById(long id){

       // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();    // Uses RestTemplate to call external API

        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody(); //whatever was the response body convert it into fakestoreproductdto

        GenericProductDTO product = new GenericProductDTO();
        product.setImage(fakeStoreProductDTO.getImage());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());

        //responseEntity.getStatusCode();
        return product;

    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO[]> response= restTemplate.getForEntity(productRequestGetURL, FakeStoreProductDTO[].class);

        List<GenericProductDTO> answer = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : Arrays.stream(response.getBody()).toList()){
            GenericProductDTO product = new GenericProductDTO();
            product.setImage(fakeStoreProductDTO.getImage());
            product.setCategory(fakeStoreProductDTO.getCategory());
            product.setTitle(fakeStoreProductDTO.getTitle());
            product.setPrice(fakeStoreProductDTO.getPrice());
            product.setDescription(fakeStoreProductDTO.getDescription());

            answer.add(product);
        }


        return answer;
    }
}
