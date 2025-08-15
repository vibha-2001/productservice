package dev.vibha.productservice.controllers;

import dev.vibha.productservice.dtos.GenericProductDTO;
import dev.vibha.productservice.models.Product;
import dev.vibha.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping ("{id}")
    public GenericProductDTO getProductById(@PathVariable ("id") long id){
        return productService.getProductById(id);

    }

    @DeleteMapping ("{id}")
    public void deleteProductById(){

    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){ //please convert request body to genericproductdto
        return productService.createProduct(product);
    }

    @PutMapping ("{id}")
    public void updateProductById(){

    }
}
