package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Promotion;
import com.example.project_client.repository.PromotionRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class PromotionViewModel {
    private final PromotionRepository promotionRepository = new PromotionRepository();
    @Setter
    private LocalDate startDate;
    @Getter
    private Promotion promotion;
    public void initModel(Promotion promotion){
        this.promotion = promotion;
    }
}
