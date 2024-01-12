package com.example.project_client;

import atlantafx.base.theme.*;
import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        Router.bind(this, stage, "CoffeeShop", 1920, 1080);
        init();
        Router.goTo(Pages.LOGIN_VIEW);
    }
    public void init(){
        Router.setRouter(Pages.LOGIN_VIEW, "yin/login-view.fxml");
        Router.setRouter(Pages.ADMIN_VIEW, "yin/admin-view.fxml");
        Router.setRouter(Pages.CUSTOMER_VIEW, "yin/customer-view.fxml");
        Router.setRouter(Pages.ADD_CUSTOMER_VIEW, "yin/add-customer-view.fxml");
        Router.setRouter(Pages.UPDATE_CUSTOMER_VIEW, "yin/update-customer-view.fxml");
        Router.setRouter(Pages.READ_CUSTOMER_VIEW, "yin/read-customer-view.fxml");
        Router.setRouter(Pages.MAIN_VIEW, "Quyen/main-view.fxml");
        Router.setRouter(Pages.CREATE_ORDER_VIEW, "Quyen/create-order-view.fxml");
        Router.setRouter(Pages.ORDER_BILL_VIEW, "Quyen/order-bill-view.fxml");
        Router.setRouter(Pages.CONFIRMATION_VIEW, "Quyen/confirmation-view.fxml");
        Router.setRouter(Pages.MAIN_VIEW_PROFIT, "Khai/main-view.fxml");
        Router.setRouter(Pages.SALARY_CAL_VIEW, "Khai/SalaryCalView.fxml");
        Router.setRouter(Pages.BILL_INGREDIENT_CAL_VIEW, "Khai/BillIngredientCalView.fxml");
        Router.setRouter(Pages.BILL_PRODUCT_CAL_VIEW, "Khai/BillProductCalView.fxml");
        Router.setRouter(Pages.PROFIT_CAL_VIEW, "Khai/ProfitCalView.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}