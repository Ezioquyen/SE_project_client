package com.example.project_client.view.controller.Quyen;

import com.example.project_client.model.Promotion;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.viewModel.Quyen.PromotionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PromotionView {
    @FXML
    TextField name;
    @FXML
    TextArea information;
    @FXML
    DatePicker startTime;
    @FXML
    DatePicker endTime;
    @FXML
    CheckBox condition;
    private final PromotionViewModel promotionViewModel = new PromotionViewModel();

    @FXML
    public void initialize() {
        promotionViewModel.initModel((Promotion) Router.getData(Pages.SELECT_PRODUCT_FOR_PROMOTION_VIEW));

    }

    @FXML
    void confirmation() {
        //TODO
    }

    @FXML
    void cancel() {
        Router.closeDialog();
    }

    private void showWarning() {

    }
}
