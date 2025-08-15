package dev.vibha.productservice.dtos;

import dev.vibha.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDTO {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
