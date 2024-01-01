package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import javafx.beans.property.SimpleIntegerProperty;

import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrderViewModel {
    private final ProductRepository productRepository = new ProductRepository();
    @Getter
    private List<Product> products;
    private final List<Product> productList = new ArrayList<>();
    @Getter
    private Map<Integer, SimpleIntegerProperty> count = new HashMap<>();


    public void initData() throws IOException {
        products = productRepository.getProductsApi();
    }

    public void addMoreProduct(Product product) {
        count.get(product.getId()).set(count.get(product.getId()).getValue()+1);
    }

    public void reduceProduct(Product product) {
             count.get(product.getId()).set(count.get(product.getId()).getValue()-1);
        if (count.get(product.getId()).getValue()==0){
            productList.remove(product);
            count.remove(product.getId());
        }
    }
    public void initCount(Product product){
            productList.add(product);
            count.put(product.getId(), new SimpleIntegerProperty(1));
    }
    public boolean check(Product product){
            return productList.contains(product);
    }

}
