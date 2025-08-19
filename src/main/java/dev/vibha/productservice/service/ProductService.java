package dev.vibha.productservice.service;


import dev.vibha.productservice.dtos.GenericProductDTO;
import dev.vibha.productservice.models.Product;

import java.util.List;

public interface ProductService {

GenericProductDTO getProductById(long id);
GenericProductDTO createProduct(GenericProductDTO product);

List<GenericProductDTO> getAllProduct();

GenericProductDTO deleteProduct(long id);
}
