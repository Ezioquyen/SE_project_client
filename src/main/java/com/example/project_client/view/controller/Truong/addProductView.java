package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.io.IOException;

public class addProductView {
    @Getter
    private Product product = new Product();
    @FXML
    private ChoiceBox<Boolean> choiceBox;
    @FXML
    private TextField name, price, discount, image;
    @FXML
    private Label nameAlert, priceAlert, discountAlert, imageAlert;
    private final Boolean[] available = {Boolean.FALSE, Boolean.TRUE};
    private Boolean[] check = {true, false, false, false, false, true};
    @FXML
    public void initialize() throws Exception {
        choiceBox.getItems().addAll(available);
        System.out.println("Add product");
        setField();
    }
    @FXML
    public void cancel() throws IOException {
        raiseAlert("Cancel add product");
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
    @FXML
    public void confirm() throws Exception {
        try {
            for(int i = 0; i < 6; ++i) {
                if (!check[i]) {
                    throw new Exception("Invalid Field");
                }
            }
            ProductRepository.saveProduct(product);
            raiseAlert("Added Product");
            Router.switchTo(Pages.PRODUCT_VIEW);
        }
        catch (Exception e) {
            raiseAlert(e.getMessage());
        }
    }
    private void raiseAlert(String alertText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Notification");
        alert.setContentText(alertText);
        alert.show();
    }
    private void setField() {
        setName();
        setPrice();
        setDiscount();
        setImage();
        choiceBox.setValue(product.getAvailable());
    }
    private void setName(){
        name.setPromptText("input name");
        name.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                if(!product.setName(newValue)){
                    throw new Exception("Invalid name, name must be a String");
                }
                check[1] = true;
                nameAlert.setText("");
            }
            catch (Exception e) {
                check[1] = false;
                nameAlert.setText(e.getMessage());
            }
        });
    }

    private void setPrice() {
        price.setPromptText("input price");
        price.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                try {
                    if (!product.setPrice(Integer.parseInt(newValue))) {
                        throw new Exception();
                    }
                }
                catch (Exception e) {
                    throw new Exception("Invalid price, price must be a number from 0 to 1000000");
                }
                check[2] = true;
                priceAlert.setText("");
            }
            catch (Exception e){
                check[2] = false;
                priceAlert.setText(e.getMessage());
            }
        });
    }
    private void setDiscount() {
        discount.setPromptText("input discount");
        discount.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try{
                try {
                    if (!product.setDiscount(Double.parseDouble(newValue))) {
                        throw new Exception();
                    }
                }
                catch (Exception e) {
                    throw new Exception("Invalid Discount, discount must be a real number from 0 to 100");
                }
                check[3] = true;
                discountAlert.setText("");
            }
            catch (Exception e){
                check[3] = false;
                discountAlert.setText(e.getMessage());
            }
        });
    }
    private void setImage() {
        image.setPromptText("input image");
        image.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                product.setImage(newValue);
                check[4] = true;
                imageAlert.setText("");
            }
            catch (Exception e){
                check[4] = false;
                imageAlert.setText("Invalid Image");
            }
        });
    }
}
