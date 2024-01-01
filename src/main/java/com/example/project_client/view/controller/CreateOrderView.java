package com.example.project_client.view.controller;

import com.example.project_client.FXRouter;
import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CreateOrderView {
    @FXML
    VBox root;
    @FXML
    void initialize(){

    }
    @FXML
    public void switchToConfirmationView(ActionEvent event) throws IOException {
        FXRouter.goTo(Pages.CONFIRMATION_VIEW.name(), Router.getRoot());
    }
}
