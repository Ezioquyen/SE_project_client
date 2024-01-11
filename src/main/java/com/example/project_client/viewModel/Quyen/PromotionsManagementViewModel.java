package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Promotion;
import com.example.project_client.repository.PromotionRepository;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromotionsManagementViewModel {
    private final PromotionRepository promotionRepository = new PromotionRepository();
    @Getter
    private List<Promotion> promotions = new ArrayList<>();
    public void initModel() throws IOException {
        loadPromotions();
    }
    private void loadPromotions() throws IOException {
        promotionRepository.getAllPromotion().forEach(e-> promotions.add(Promotion.fromData(e)));
    }
}
