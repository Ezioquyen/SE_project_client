package com.example.project_client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Boolean available;
    private Double discount;
    private String image;
    @JsonCreator
    public Product(@JsonProperty("id") Integer id,@JsonProperty("name") String name, @JsonProperty("price") Integer price,@JsonProperty("available") Boolean available,@JsonProperty("discount") Double discount,@JsonProperty("image") String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.discount = discount;
        this.image = image;
    }
}
