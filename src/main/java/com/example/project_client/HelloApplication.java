package com.example.project_client;

import atlantafx.base.theme.NordLight;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.yin.AddCustomerView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        Router.bind(this, stage, "CoffeeShop", 1680, 800);
        init();
        Router.goTo(Pages.CUSTOMER_VIEW);

    }
    public void init(){
        Router.setRouter(Pages.LOGIN_VIEW, "yin/login-view.fxml");
        Router.setRouter(Pages.ADMIN_VIEW, "yin/admin-view.fxml");
        Router.setRouter(Pages.MAIN_VIEW, "Quyen/main-view.fxml");
        Router.setRouter(Pages.CREATE_ORDER_VIEW, "Quyen/create-order-view.fxml");
        Router.setRouter(Pages.ORDER_BILL_VIEW, "Quyen/order-bill-view.fxml");
        Router.setRouter(Pages.CONFIRMATION_VIEW, "Quyen/confirmation-view.fxml");
        Router.setRouter(Pages.CUSTOMER_VIEW, "yin/customer-view.fxml");
        Router.setRouter(Pages.ADD_CUSTOMER_VIEW, "yin/add-customer-view.fxml");
        AddCustomerView addCustomerView = new AddCustomerView();
        Router.setData(Pages.ADD_CUSTOMER_VIEW, addCustomerView);

    }
    public static void main(String[] args) {
        launch();
    }
}