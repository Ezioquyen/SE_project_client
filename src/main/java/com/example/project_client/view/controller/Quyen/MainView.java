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
}
