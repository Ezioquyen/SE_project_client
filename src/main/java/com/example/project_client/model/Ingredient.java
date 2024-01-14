package com.example.project_client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ingredient {
    private Integer id;
    private String name;
    private String unit;
    private Integer unit_Price;

    @JsonCreator
    public Ingredient(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("unit") String unit, @JsonProperty("unit_Price") Integer unit_Price){
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unit_Price = unit_Price;
    }

    public Ingredient(){
        this.id = 1;
        this.name = null;
        this.unit = null;
        this.unit_Price = null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getUnit_Price() {
        return unit_Price;
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

    public Boolean setUnit(String unit) {
        if(unit.equals("")){
            return Boolean.FALSE;
        }
        else {
            this.unit = unit;
            return Boolean.TRUE;
        }
    }

    public Boolean setUnit_Price(Integer unit_Price) {
        if(unit_Price > 1000000 || unit_Price < 0){
            return Boolean.FALSE;
        }
        else {
            this.unit_Price = unit_Price;
            return Boolean.TRUE;
        }
    }
}
