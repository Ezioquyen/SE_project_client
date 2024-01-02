package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import javafx.beans.property.SimpleIntegerProperty;

import lombok.Getter;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrderViewModel {
    private final ProductRepository productRepository = new ProductRepository();
    @Getter
    private List<Product> products;
    @Getter
    private Map<Product, SimpleIntegerProperty> count = new HashMap<>();
    @Getter
    private final SimpleIntegerProperty total = new SimpleIntegerProperty(0);


    public void initData() throws IOException {
        products = productRepository.getProductsApi();
    }

    public void addMoreProduct(Product product) {
        count.get(product).set(count.get(product).getValue() + 1);
        total.setValue(total.getValue() + product.getPrice());
    }

    public void reduceProduct(Product product) {
        count.get(product).set(count.get(product).getValue() - 1);
        total.setValue(total.getValue() - product.getPrice());
        if (count.get(product).getValue() == 0) {
            count.remove(product);
        }
    }

    public void initCount(Product product) {
        count.put(product, new SimpleIntegerProperty(1));
        total.setValue(total.getValue() + product.getPrice());
    }

    public boolean check(Product product) {
        return count.get(product)==null;
    }
}
