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
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        Router.bind(this, stage, "Hello World", 1920, 1080);
        init();
        Router.goTo(Pages.MAIN_VIEW);

    }

    public void init() {
        Router.setRouter(Pages.MAIN_VIEW, "Quyen/main-view.fxml");
        Router.setRouter(Pages.CREATE_ORDER_VIEW, "Quyen/create-order-view.fxml");
        Router.setRouter(Pages.ORDER_BILL_VIEW, "Quyen/order-bill-view.fxml");
        Router.setRouter(Pages.PROMOTION_VIEW, "Quyen/promotion-view.fxml");
        Router.setRouter(Pages.CUSTOMER_INFORMATION_INPUT_VIEW, "Quyen/customer-information-input-view.fxml");




        Router.setRouter(Pages.MAIN_VIEW_PROFIT, "Khai/main-view.fxml");
        Router.setRouter(Pages.SALARY_CAL_VIEW, "Khai/SalaryCalView.fxml");
        Router.setRouter(Pages.BILL_INGREDIENT_CAL_VIEW, "Khai/BillIngredientCalView.fxml");
        Router.setRouter(Pages.BILL_PRODUCT_CAL_VIEW, "Khai/BillProductCalView.fxml");
        Router.setRouter(Pages.PROFIT_CAL_VIEW, "Khai/ProfitCalView.fxml");



        Router.setRouter(Pages.INGREDIENT_VIEW, "Truong/ingredientScene.fxml");
        Router.setRouter(Pages.ADD_INGREDIENT, "Truong/addIngredient.fxml");
        Router.setRouter(Pages.CHANGE_INGREDIENT, "Truong/changeIngredient.fxml");
        Router.setRouter(Pages.PRODUCT_VIEW, "Truong/productScene.fxml");
        Router.setRouter(Pages.ADD_PRODUCT, "Truong/addProduct.fxml");
        Router.setRouter(Pages.CHANGE_PRODUCT, "Truong/changeProduct.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}