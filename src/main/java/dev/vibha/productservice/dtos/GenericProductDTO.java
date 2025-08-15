package dev.vibha.productservice.dtos;

import dev.vibha.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
