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
        Router.bind(this, stage, "Hello World", 1920, 1080);
        init();
        Router.goTo(Pages.MAIN_VIEW_PROFIT, true);

    }
    public void init(){
        Router.setRouter(Pages.MAIN_VIEW, "Quyen/main-view.fxml");
        Router.setRouter(Pages.CREATE_ORDER_VIEW, "Quyen/create-order-view.fxml");
        Router.setRouter(Pages.ORDER_BILL_VIEW, "Quyen/order-bill-view.fxml");

        Router.setRouter(Pages.CONFIRMATION_VIEW, "Quyen/confirmation-view.fxml");
        Router.setRouter(Pages.MAIN_VIEW_PROFIT, "Khai/main-view.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}