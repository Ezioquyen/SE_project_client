package com.example.project_client.view.controller.Quyen;

import com.example.project_client.FXRouter;
import com.example.project_client.model.Product;
import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.ProductView;
import com.example.project_client.viewModel.Quyen.CreateOrderViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;

public class CreateOrderView {
    @FXML
    VBox root;
    @FXML
    FlowPane productsPane;
    CreateOrderViewModel createOrderViewModel = new CreateOrderViewModel();
    @FXML
    void initialize() throws IOException {
        createOrderViewModel.initData();
        for(Product product : createOrderViewModel.getProducts()){
            productsPane.getChildren().add(new ProductView(product.getName(), NumberFormat.getNumberInstance(Locale.US).format(product.getPrice()),product.getAvailable()?"Còn hàng":"Hết hàng", product.getImage()));
        }
    }
    @FXML
    public void switchToConfirmationView(ActionEvent event) throws IOException {
        FXRouter.goTo(Pages.CONFIRMATION_VIEW.name(), Router.getRoot());
    }
}
