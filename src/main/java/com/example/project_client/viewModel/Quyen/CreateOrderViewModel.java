package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import lombok.Getter;

import java.io.IOException;
import java.util.List;

public class CreateOrderViewModel {
    private final ProductRepository productRepository = new ProductRepository();
    @Getter
    private List<Product> products;
    public void initData() throws IOException {
        products = productRepository.getProductsApi();
    }
}
