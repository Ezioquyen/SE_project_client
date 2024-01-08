package com.example.project_client.view.controller.yin;

import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AdminView {

    @FXML
    private HBox ProductBox;

    @FXML
    private BorderPane SceneBorderPane;

    @FXML
    private HBox acceptOrderBox;

    @FXML
    private HBox addUserBox;

    @FXML
    private Button addUserBtn;

    @FXML
    private HBox changePasswordBox;

    @FXML
    private HBox checkHistoryBox;

    @FXML
    private Button customerBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private HBox pendingOrderBox;

    @FXML
    private Button productBtn;

    @FXML
    private HBox revenue;

    @FXML
    private Button revenueBtn;

    @FXML
    private Button userBtn;

    @FXML
    void HandleAddUserClicked(ActionEvent event) {

    }

    @FXML
    void HandleCustomerClicked(ActionEvent event) throws IOException {
        Router.switchTo(Pages.CUSTOMER_VIEW);

    }

    @FXML
    void HandleProductClicked(ActionEvent event) {

    }

    @FXML
    void HandleRevenueClicked(ActionEvent event) {

    }

    @FXML
    void HandleStaffClicked(ActionEvent event) {

    }

}

