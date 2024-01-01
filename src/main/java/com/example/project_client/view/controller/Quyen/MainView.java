package com.example.project_client.view.controller.Quyen;

import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainView {
    @FXML
    VBox vBox;
    @FXML
    void initialize(){
        Router.setRoot(vBox);
    }
    @FXML
    public void switchToCreateOrderView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.CREATE_ORDER_VIEW);
    }
}
