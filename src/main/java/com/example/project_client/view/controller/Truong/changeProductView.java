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

import java.io.IOException;

public class changeProductView {
    @FXML
    private ChoiceBox<Boolean> choiceBox;
    private Boolean[] available = {Boolean.FALSE, Boolean.TRUE};
    private Boolean[] check = {true, true, true, true, true, true};
    @FXML
    private TextField name, price, discount, image;
    @FXML
    private Label id, nameAlert, priceAlert, discountAlert, imageAlert;
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
            for(int i = 0; i < 6; ++i){
                if(!check[i]){
                    throw new Exception("Cannot change product");
                }
            }
            ProductRepository.updateProduct(product);
            raiseAlert("Changed Product");
            Router.switchTo(Pages.PRODUCT_VIEW);
        }
        catch (Exception e){
            raiseAlert(e.getMessage().toString());
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
        id.setText(product.getId().toString());
        setName();
        setPrice();
        setDiscount();
        setImage();
        choiceBox.setValue(product.getAvailable());
    }
    private void setName(){
        name.setPromptText(product.getName());
        name.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                if(!product.setName(newValue)){
                    throw new Exception();
                }
                check[1] = true;
                nameAlert.setText("");
            }
            catch (Exception e) {
                check[1] = false;
                product.setName(name.getPromptText());
                nameAlert.setText("Invalid name, name must be a String");
            }
        });
    }

    private void setPrice() {
        price.setPromptText(product.getPrice().toString());
        price.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                if(!product.setPrice(Integer.parseInt(newValue))){
                    throw new Exception();
                }
                check[2] = true;
                priceAlert.setText("");
            }
            catch (Exception e){
                check[2] = false;
                product.setPrice(Integer.parseInt(price.getPromptText()));
                priceAlert.setText("Invalid price, price must be a number from 0 to 1000000");
            }
        });
    }
    private void setDiscount() {
        discount.setPromptText(product.getDiscount().toString());
        discount.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try{
                if(!product.setDiscount(Double.parseDouble(newValue))){
                    throw new Exception();
                }
                check[3] = true;
                discountAlert.setText("");
            }
            catch (Exception e){
                check[3] = false;
                product.setDiscount(Double.parseDouble(discount.getPromptText()));
                discountAlert.setText("Invalid Discount, discount must be a real number from 0 to 100");
            }
        });
    }
    private void setImage() {
        image.setPromptText(product.getImage());
        image.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                product.setImage(newValue);
                check[4] = true;
                imageAlert.setText("");
            }
            catch (Exception e){
                check[4] = false;
                product.setImage(image.getPromptText());
                imageAlert.setText("Invalid Image");
            }
        });
    }
}
