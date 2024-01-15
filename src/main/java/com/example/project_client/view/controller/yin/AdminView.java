package com.example.project_client.view.controller.yin;

import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Optional;

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
        Parent customerView = Router.loadTo(Pages.CUSTOMER_VIEW);
        SceneBorderPane.setRight(customerView);

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
    @FXML
    void HandleSignOut(ActionEvent event) throws IOException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận đăng xuất");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Bạn có chắc chắn muốn đăng xuất?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            Router.switchTo(Pages.LOGIN_VIEW);
        } else {

        }
    }
}

