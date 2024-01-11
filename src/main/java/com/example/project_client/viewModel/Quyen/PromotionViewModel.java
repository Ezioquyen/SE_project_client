package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Promotion;
import com.example.project_client.repository.PromotionRepository;
import lombok.Getter;


import java.io.IOException;
import java.time.LocalDate;


public class PromotionViewModel {
    private final PromotionRepository promotionRepository = new PromotionRepository();
    @Getter
    private Promotion promotion;
    public void initModel(Promotion promotion){
        this.promotion = promotion;
    }
    public Boolean check(LocalDate date) throws IOException {
       return promotionRepository.checkPromotion(date, promotion!=null?promotion.getId():0);
    }
    public void createPromotion() throws Exception {
        promotionRepository.savePromotion(Promotion.toData(promotion));
    }
}
