package dev.vibha.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
    private long id;
}
