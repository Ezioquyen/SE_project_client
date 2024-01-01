package com.example.project_client.view.controller.Quyen;

import com.example.project_client.FXRouter;
import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.ProductView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CreateOrderView {
    @FXML
    VBox root;
    @FXML
    FlowPane productsPane;
    @FXML
    void initialize(){
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
        productsPane.getChildren().add(new ProductView("Cà phê đen","29.999đ","Còn hàng","/com/example/project_client/images/img.png"));
    }
    @FXML
    public void switchToConfirmationView(ActionEvent event) throws IOException {
        FXRouter.goTo(Pages.CONFIRMATION_VIEW.name(), Router.getRoot());
    }
}
