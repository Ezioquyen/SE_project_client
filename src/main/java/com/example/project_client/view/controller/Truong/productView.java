package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Product;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public final class productView implements Initializable {
    private static Product product;
    public static Product getProduct() {
        return product;
    }
    @FXML
    private TableColumn<Product, Integer> id;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Integer> price;
    @FXML
    private TableColumn<Product, Boolean> available;
    @FXML
    private TableColumn<Product, Double> discount;
    @FXML
    private TableColumn<Product, String> image;

    public static void setProduct(Product product) {
        productView.product = product;
    }

    @FXML
    TableView<Product> tableView;
    @Getter
    private List<Product> products;
    @FXML
    private ObservableList<Product> tableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            products = ProductRepository.getProductsApi();
            tableList = FXCollections.observableArrayList(products);
            tableView.setItems(tableList);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            available.setCellValueFactory(new PropertyValueFactory<>("available"));
            discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
            image.setCellValueFactory(new PropertyValueFactory<>("image"));
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
    @FXML
    public void addProduct(ActionEvent event) throws IOException {
        Router.switchTo(Pages.ADD_PRODUCT);
    }
    @FXML
    public void changeProduct(ActionEvent event) throws IOException {
        try {
            product = tableView.getSelectionModel().getSelectedItem();
            Router.switchTo(Pages.CHANGE_PRODUCT);
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }
    @FXML
    public void deleteProduct(ActionEvent event) throws Exception {
        try {
            product = tableView.getSelectionModel().getSelectedItem();
            ProductRepository.deleteProduct(product.getId().toString());
            tableList.remove(product);
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }
}
