package com.example.project_client.event;

import com.example.project_client.model.Product;
import com.example.project_client.model.Promotion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Provider {
    @Setter
    private Promotion promotion;
    private static final Provider INSTANCE = new Provider();
    private List<Product> products;
    private Provider(){

    }
    public static Provider getInstance(){
        return  INSTANCE;
    }
}
