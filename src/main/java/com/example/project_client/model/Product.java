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
    public Product(){
        this.id = 1;
        this.name = null;
        this.price = null;
        this.available = null;
        this.discount = null;
        this.image = null;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Double getDiscount() {
        return discount;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
