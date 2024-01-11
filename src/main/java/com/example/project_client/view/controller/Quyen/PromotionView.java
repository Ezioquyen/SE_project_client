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
import javafx.scene.layout.VBox;

import java.io.IOException;


public class PromotionView {
    private Boolean isWarn = false;
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
    @FXML
    VBox warning;
    @FXML
    VBox parent;
    private final PromotionViewModel promotionViewModel = new PromotionViewModel();

    @FXML
    public void initialize() {
        parent.getChildren().remove(warning);
        promotionViewModel.initModel((Promotion) Router.getData(Pages.SELECT_PRODUCT_FOR_PROMOTION_VIEW));
        name.setText(promotionViewModel.getPromotion().getName());
        information.setText(promotionViewModel.getPromotion().getInformation());
        startTime.setValue(promotionViewModel.getPromotion().getStartDate());
        endTime.setValue(promotionViewModel.getPromotion().getEndDate());
        condition.setSelected(promotionViewModel.getPromotion().getNeedCondition());
        name.textProperty().addListener((observableValue, s, t1) -> promotionViewModel.getPromotion().setName(t1));
        information.textProperty().addListener(((observableValue, s, t1) -> promotionViewModel.getPromotion().setInformation(t1)));
        startTime.valueProperty().addListener((observableValue, date, t1) -> promotionViewModel.getPromotion().setStartDate(t1));
        endTime.valueProperty().addListener(((observableValue, date, t1) -> promotionViewModel.getPromotion().setEndDate(t1)));
        condition.selectedProperty().addListener((observableValue, aBoolean, t1) -> promotionViewModel.getPromotion().setNeedCondition(t1));
    }

    @FXML
    void confirmation() throws Exception {
        if (showWarning()) {
            promotionViewModel.createPromotion();
            Router.removeData(Pages.PROMOTIONS_MANAGEMENT);
            Router.removeData(Pages.SELECT_PRODUCT_FOR_PROMOTION_VIEW);
            Router.closeDialog();
            Router.switchTo(Pages.PROMOTIONS_MANAGEMENT);
        }


    }

    @FXML
    void cancel() {
        Router.closeDialog();
    }

    private Boolean showWarning() throws IOException {
        if (name.getText().isEmpty()
                || information.getText().isEmpty()
                || startTime.getValue().isAfter(endTime.getValue())
                || !promotionViewModel.check(startTime.getValue())) {
            if (!isWarn) {
                parent.getChildren().add(warning);
                isWarn = true;
            }
            return false;
        }
        return true;
    }
}
