package com.example.project_client.view.controller.Quyen;

import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;

import javafx.fxml.FXML;


import java.io.IOException;

public class MainView {

    @FXML
    void initialize(){
    }
    @FXML
    public void switchToCreateOrderView() throws IOException {
        Router.switchTo(Pages.CREATE_ORDER_VIEW);
    }
    @FXML
    public void switchToManagePromotionView() throws IOException {
        Router.switchTo(Pages.PROMOTIONS_MANAGEMENT);
    }
    @FXML
    public void switchToIngredientView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }
    @FXML
    public void switchToProductView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
}
