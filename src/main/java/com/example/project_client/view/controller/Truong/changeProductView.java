package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class changeProductView {
    @FXML
    private ChoiceBox<Boolean> choiceBox;
    @FXML
    private TextField id, name, price, discount, image;
    private Boolean[] available = {Boolean.FALSE, Boolean.TRUE};
    private Product product;
    @FXML
    public void initialize() throws Exception {
        choiceBox.getItems().addAll(available);
        product = productView.getProduct();
        System.out.println(product.getName());
        id.setPromptText(product.getId().toString());
        id.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            product.setId(Integer.valueOf(newValue));
        }));
        name.setPromptText(product.getName());
        name.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            product.setName(newValue);
        }));
        price.setPromptText(product.getPrice().toString());
        price.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            product.setPrice(Integer.valueOf(newValue));
        }));
        discount.setPromptText(product.getDiscount().toString());
        discount.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            product.setDiscount(Double.valueOf(newValue));
        }));
        image.setPromptText(product.getImage());
        image.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            product.setImage(newValue);
        }));
        choiceBox.setValue(product.getAvailable());
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
    @FXML
    public void confirm() throws IOException {
        try {
//            if(setProduct()){
//                ProductRepository.deleteProduct(product.getId().toString());
                ProductRepository.updateProduct(product);
//                productView.
                Router.switchTo(Pages.PRODUCT_VIEW);
//            }
//            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }

//    private Boolean setProduct(){
//        try{
//            product.setId(Integer.parseInt(id.getText()));
//            product.setName(name.getText());
//            product.setPrice(Integer.parseInt(price.getText()));
//            product.setDiscount(Double.parseDouble(discount.getText()));
//            product.setImage(image.getText());
//            product.setAvailable(choiceBox.getValue());
//            return Boolean.TRUE;
//        }
//        catch (Exception ex){
//            System.out.println("ERROR");
//            return Boolean.FALSE;
//        }
//    }
}
