package com.example.project_client.view.controller.Khai;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void openWdSalaryCalView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.SALARY_CAL_VIEW);
    }
    @FXML
    public void openWdBillProductCalView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.BILL_PRODUCT_CAL_VIEW);
    }
    @FXML
    public void openWdBillIngredientCalView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.BILL_INGREDIENT_CAL_VIEW);
    }
    @FXML
    public void openWdProfitCalView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.PROFIT_CAL_VIEW);
    }
    @FXML
    private Button openWdSalaryCalViewBtn;
    @FXML
    private Button openWdBillProductCalViewBtn;
    @FXML
    private Button openWdBillIngredientCalViewBtn;
    @FXML
    private Button openWdProfitCalViewBtn;

}
