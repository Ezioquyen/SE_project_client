package com.example.project_client.view.controller.Quyen;

import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.PromotionChild;
import com.example.project_client.viewModel.Quyen.PromotionsManagementViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

public class PromotionsManagementView {
    @FXML
    ListView<PromotionChild> listView;
    private final PromotionsManagementViewModel promotionsManagementViewModel = new PromotionsManagementViewModel();
    @FXML
    void initialize() throws IOException {
        promotionsManagementViewModel.initModel();
        promotionsManagementViewModel.getPromotions().forEach(e->listView.getItems().add(new PromotionChild(e)));
    }
    @FXML
    void create() throws IOException {
        Router.switchTo(Pages.SELECT_PRODUCT_FOR_PROMOTION_VIEW);
    }
    @FXML
    void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
}
