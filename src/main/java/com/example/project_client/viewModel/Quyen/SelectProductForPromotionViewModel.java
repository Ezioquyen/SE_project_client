package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Product;
import com.example.project_client.model.Promotion;
import com.example.project_client.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
@Getter
@Setter
public class SelectProductForPromotionViewModel {
    private Integer emptyText = 0;

    private List<Product> products;
    private Promotion promotion = new Promotion();
    private final ProductRepository productRepository = new ProductRepository();
    public void initData() throws IOException {
        products = productRepository.getProductsApi();
    }
}
