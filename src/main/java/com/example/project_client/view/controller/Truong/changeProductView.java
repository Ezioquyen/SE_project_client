package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class changeProductView {
    @FXML
    private ChoiceBox<Boolean> choiceBox;
    private Boolean[] available = {Boolean.FALSE, Boolean.TRUE};
    @FXML
    private TextField id, name, price, discount, image;
    private Product product;
    @FXML
    private void initialize() throws Exception {
        choiceBox.getItems().addAll(available);
        product = productView.getProduct();
        System.out.println("Change product: "+product.getName());
        setField();
    }
    @FXML
    private void cancel() throws IOException {
        raiseAlert("Cancel change product");
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
    @FXML
    private void confirm() throws IOException {
        try {
            ProductRepository.updateProduct(product);
            raiseAlert("Changed Product");
            Router.switchTo(Pages.PRODUCT_VIEW);
        }
        catch (Exception e){
            raiseAlert(e.toString());
        }
    }

    private void raiseAlert(String alertText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Notification");
        alert.setContentText(alertText);
        alert.show();
    }
    private void setField() throws Exception {
        try {
            id.setPromptText(product.getId().toString());
            id.textProperty().addListener((observableValue, oldValue, newValue) -> {
                product.setId(Integer.valueOf(newValue));
            });
            name.setPromptText(product.getName());
            name.textProperty().addListener((observableValue, oldValue, newValue) -> {
                product.setName(newValue);
            });
            price.setPromptText(product.getPrice().toString());
            price.textProperty().addListener((observableValue, oldValue, newValue) -> {
                product.setPrice(Integer.valueOf(newValue));
            });
            discount.setPromptText(product.getDiscount().toString());
            discount.textProperty().addListener((observableValue, oldValue, newValue) -> {
                product.setDiscount(Double.valueOf(newValue));
            });
            image.setPromptText(product.getImage());
            image.textProperty().addListener((observableValue, oldValue, newValue) -> {
                product.setImage(newValue);
            });
            choiceBox.setValue(product.getAvailable());
        }
        catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
