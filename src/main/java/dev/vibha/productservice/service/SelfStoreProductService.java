package dev.vibha.productservice.service;

import dev.vibha.productservice.dtos.GenericProductDTO;
import dev.vibha.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImp")
public class SelfStoreProductService implements ProductService{
    public GenericProductDTO getProductById(long id){
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        return null;
    }
}
