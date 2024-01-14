package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Product;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class readProductView {
    @FXML
    private Label id, name, price, available, discount, image;
    private Product product;
    @FXML
    private void initialize() throws Exception {
        product = productView.getProduct();
        System.out.println("Read Product: "+product.getName());
        getInformation();
    }
    @FXML
    private void cancel() throws Exception {
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
    private void getInformation() {
        id.setText(product.getId().toString());
        name.setText(product.getName());
        price.setText(product.getPrice().toString());
        available.setText(product.getAvailable().toString());
        discount.setText(product.getDiscount().toString());
        image.setText(product.getImage());
    }
}
