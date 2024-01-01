package com.example.project_client;

import com.example.project_client.data.JsonUtils;
import com.example.project_client.model.User;
import com.example.project_client.router.Pages;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "Hello World", 300, 275);
        init();
        FXRouter.goTo(Pages.MAIN_VIEW.name());

    }
    public void init(){
        FXRouter.when(Pages.MAIN_VIEW.name(), "Quyen/main-view.fxml");
        FXRouter.when(Pages.CREATE_ORDER_VIEW.name(), "Quyen/create-order-view.fxml");
        FXRouter.when(Pages.CONFIRMATION_VIEW.name(), "Quyen/confirmation-view.fxml");
    }
    public static void main(String[] args) {



        launch();
    }
}