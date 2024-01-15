package com.example.project_client.view.controller.yin;

import com.example.project_client.HelloApplication;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class AdminView {


    @FXML
    VBox mainPane;

    @FXML
    void initialize() throws IOException {
        //Quyen
        String scenePath = Router.getRouterLabel().get(Pages.MAIN_VIEW);
        Parent resource = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(scenePath)));
        mainPane.getChildren().setAll(resource);
    }

    @FXML
    void HandleAddUserClicked(ActionEvent event) {

    }

    @FXML
    void HandleCustomerClicked(ActionEvent event) throws IOException {
        Router.goTo(Pages.CUSTOMER_VIEW);
        // Router.showDialog(Pages.CUSTOMER_VIEW);
    }


    @FXML
    void HandleProductClicked(ActionEvent event) {

    }

    @FXML
    void HandleRevenueClicked(ActionEvent event) {

    }

    @FXML
    void HandleStaffClicked(ActionEvent event) throws IOException{
        Router.switchTo(Pages.STAFF_VIEW);

    }

    @FXML
    void HandleSignOut(ActionEvent event) throws IOException {
        Router.goTo(Pages.LOGIN_VIEW);
    }

    @FXML
    void switchToMainView() throws IOException {
        String scenePath = Router.getRouterLabel().get(Pages.MAIN_VIEW);
        Parent resource = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(scenePath)));
        mainPane.getChildren().setAll(resource);
    }
}

