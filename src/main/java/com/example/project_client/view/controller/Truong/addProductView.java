package com.example.project_client.view.controller.Truong;

import com.example.project_client.data.Api;
import com.example.project_client.data.JsonUtils;
import com.example.project_client.data.Request;
import com.example.project_client.model.Customer;
import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.io.IOException;
import java.util.Objects;

public class addProductView {
    @Getter
    private Product product = new Product();
    @FXML
    private ChoiceBox<Boolean> choiceBox;
    @FXML
    private TextField id, name, price, discount, image;
    private Boolean[] available = {Boolean.FALSE, Boolean.TRUE};
    @FXML
    public void initialize(){
        choiceBox.getItems().addAll(available);
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.PRODUCT_VIEW);
    }
    @FXML
    public void confirm() throws Exception {
        if(setProduct()) {
            ProductRepository.saveProduct(product);
            Router.switchTo(Pages.PRODUCT_VIEW);
        }
    }
    private Boolean setProduct(){
        try{
            product.setId(Integer.parseInt(id.getText()));
            product.setName(name.getText());
            product.setPrice(Integer.parseInt(price.getText()));
            product.setDiscount(Double.parseDouble(discount.getText()));
            product.setImage(image.getText());
            product.setAvailable(choiceBox.getValue());
            return Boolean.TRUE;
        }
        catch (Exception ex){
            System.out.println("ERROR");
            return Boolean.FALSE;
        }
    }
}
