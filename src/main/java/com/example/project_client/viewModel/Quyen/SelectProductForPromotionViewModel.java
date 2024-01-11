package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Product;
import com.example.project_client.model.Promotion;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.repository.PromotionRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
@Getter
@Setter
public class SelectProductForPromotionViewModel {
    private Integer emptyText = 0;
    private Boolean isCreate = false;
    private List<Product> products;
    private Promotion promotion;
    private final PromotionRepository promotionRepository = new PromotionRepository();
    private final ProductRepository productRepository = new ProductRepository();
    public void initData(Promotion promotion) throws IOException {
        if(promotion!=null) {this.promotion = promotion;
           }
        else {this.promotion = new Promotion();
            isCreate = true;}
        products = productRepository.getProductsApi();
    }
    public void removePromo() throws IOException {
        promotionRepository.removePromotion(promotion.getId());
    }
}
