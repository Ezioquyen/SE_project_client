package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
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
    @FXML
    public void initialize() {
        choiceBox.getItems().addAll(available);
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
    @FXML
    public void confirm() throws Exception {
        try {
            if(setProduct()){
                throw new Exception("Invalid Field");
            }
            ProductRepository.saveProduct(product);
            Router.switchTo(Pages.PRODUCT_VIEW);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    private Boolean setProduct() {
        Boolean check = Boolean.FALSE;
        try {
            if(!product.setName(name.getText())){
                throw new Exception();
            }
            nameAlert.setText("");
        }
        catch (Exception e) {
            check = Boolean.TRUE;
            nameAlert.setText("Invalid name, name must be a String");
        }
        try {
            if(!product.setPrice(Integer.parseInt(price.getText()))){
                throw new Exception();
            }
        }
        catch (Exception e){
            check = Boolean.TRUE;
            System.out.println("Invalid price, price must be a number from 0 to 1000000");
        }
        try{
            if(!product.setDiscount(Double.parseDouble(discount.getText()))){
                throw new Exception();
            }
        }
        catch (Exception e){
            check = Boolean.TRUE;
            System.out.println("Invalid Discount, discount must be a real number from 0 to 100");
        }
        try {
            product.setImage(image.getText());
        }
        catch (Exception e){
            check = Boolean.TRUE;
            System.out.println("Invalid Image");
        }
        product.setAvailable(choiceBox.getValue());
        return check;
    }
}
