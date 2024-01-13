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
        this.id = null;
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

    public Boolean setName(String name) {
        if(name.equals("")){
            return Boolean.FALSE;
        }
        else {
            this.name = name;
            return Boolean.TRUE;
        }
    }

    public Boolean setDiscount(Double discount) {
        if(discount > 100 || discount < 0){
            return Boolean.FALSE;
        }
        this.discount = discount;
        return Boolean.TRUE;
    }

    public Boolean setPrice(Integer price) {
        if(price > 1000000 || price < 0){
            return Boolean.FALSE;
        }
        else {
            this.price = price;
            return Boolean.TRUE;
        }
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
