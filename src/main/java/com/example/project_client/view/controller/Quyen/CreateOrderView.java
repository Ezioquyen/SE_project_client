package com.example.project_client.view.controller.Quyen;

import com.example.project_client.model.Product;
import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.ProductCount;
import com.example.project_client.view.controller.Quyen.components.ProductView;
import com.example.project_client.viewModel.Quyen.CreateOrderViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;

import javafx.scene.layout.FlowPane;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class CreateOrderView {
    @FXML
    FlowPane productsPane;
    @FXML
    ListView<ProductCount> listView;

    @FXML
    CreateOrderViewModel createOrderViewModel = new CreateOrderViewModel();
    @FXML
    void initialize() throws IOException {
        createOrderViewModel.initData();
        for(Product product : createOrderViewModel.getProducts()){
            ProductView productView = new ProductView(product.getName(), NumberFormat.getNumberInstance(Locale.US).format(product.getPrice()),product.getAvailable()?"Còn hàng":"Hết hàng", product.getImage());

            productView.setOnMouseClicked((e)->{
                if(!createOrderViewModel.check(product)){
                    ProductCount productCount = new ProductCount(product);
                    createOrderViewModel.initCount(product);
                    createOrderViewModel.getCount().get(product.getId()).addListener(((observableValue, number, t1) -> {
                                if(t1.intValue()==0) listView.getItems().remove(productCount);
                                else {
                                    productCount.getTextField().setText(t1.toString());
                                    productCount.getLabel2().setText(NumberFormat.getNumberInstance(Locale.US).format(product.getPrice()*t1.longValue()) + " VND");
                                }
                            })
                    );
                    productCount.getSub().setOnAction((actionEvent)->{
                        createOrderViewModel.reduceProduct(product);
                    });
                    productCount.getAdd().setOnAction((actionEven)->{
                        createOrderViewModel.addMoreProduct(product);
                    });
                    listView.getItems().add(productCount);
                } else createOrderViewModel.addMoreProduct(product);
            });
            productsPane.getChildren().add(productView);
        }
    }
    @FXML
    public void showConfirmDialogView(ActionEvent event) throws IOException {
        Router.showDialog(Pages.CONFIRMATION_VIEW);
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
}
