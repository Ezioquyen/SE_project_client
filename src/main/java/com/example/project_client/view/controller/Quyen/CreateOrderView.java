package com.example.project_client.view.controller.Quyen;

import atlantafx.base.theme.Styles;
import com.example.project_client.model.Product;
import com.example.project_client.router.Pages;

import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.ProductCount;
import com.example.project_client.view.controller.Quyen.components.ProductView;
import com.example.project_client.view.controller.Quyen.interfaces.InitStyles;
import com.example.project_client.viewModel.Quyen.CreateOrderViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.layout.FlowPane;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class CreateOrderView implements InitStyles {
    @FXML
    FlowPane productsPane;
    @FXML
    ListView<ProductCount> listView;
    @FXML
    Label total;
    @FXML
    Label warning;
    @FXML
    Button create;
    @FXML
    Button cancel;
    @FXML
    CreateOrderViewModel createOrderViewModel = new CreateOrderViewModel();
    @FXML
    void initialize() throws IOException {
        initStyle();
        createOrderViewModel.initData();
        for(Product product : createOrderViewModel.getProducts()){
            ProductView productView = new ProductView(product.getName(), NumberFormat.getNumberInstance(Locale.US).format(product.getPrice()),product.getAvailable()?"Còn hàng":"Hết hàng", product.getImage());
            productView.setOnMouseClicked((e)->{
                if(createOrderViewModel.check(product)){
                    ProductCount productCount = new ProductCount(product);
                    createOrderViewModel.initCount(product);
                    createOrderViewModel.getCount().get(product).addListener(((observableValue, number, t1) -> {
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
        createOrderViewModel.getTotal().addListener((obs,oldVal,newVal)->{
            total.setText(NumberFormat.getNumberInstance(Locale.US).format(newVal.intValue())+" VND");
        });
        Router.setData(Pages.CREATE_ORDER_VIEW,createOrderViewModel);
    }
    @FXML
    public void showConfirmDialogView(ActionEvent event) throws IOException {
        if(listView.getItems().isEmpty()){
            if(!warning.isVisible()){
                warning.setVisible(true);
            }
            return;
        }
        if(warning.isVisible()){
            warning.setVisible(false);
        }
        Router.showDialog(Pages.CONFIRMATION_VIEW);
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
    @Override
   public void initStyle(){
        create.getStyleClass().add(Styles.SUCCESS);
        cancel.getStyleClass().add(Styles.DANGER);
        Styles.toggleStyleClass(listView,Styles.BORDERED);
    }
}
