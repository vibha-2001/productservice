package dev.vibha.productservice.service;


import dev.vibha.productservice.dtos.GenericProductDTO;
import dev.vibha.productservice.models.Product;

public interface ProductService {

GenericProductDTO getProductById(long id);
GenericProductDTO createProduct(GenericProductDTO product);
}
