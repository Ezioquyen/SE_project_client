package com.example.project_client.view.controller.Quyen;

import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.ProductInPromotion;
import com.example.project_client.view.controller.Quyen.components.ProductView;
import com.example.project_client.viewModel.Quyen.SelectProductForPromotionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.stream.Collectors;

public class SelectProductForPromotionView {
    @FXML
    ListView<ProductInPromotion> listView;

    @FXML
    TextField percent;
    @FXML
    FlowPane productsPane;
    @FXML
    Button addAll;
    @FXML
    Label warning;
    private final SelectProductForPromotionViewModel selectProductForPromotionViewModel = new SelectProductForPromotionViewModel();

    @FXML
    void initialize() throws IOException {
        selectProductForPromotionViewModel.initData();
        productsPane.getChildren().addAll(selectProductForPromotionViewModel.getProducts().stream().map(e -> {
            ProductView productView = new ProductView(e);
            productView.setOnMouseClicked(mouseEvent -> insertProduct(productView));
            return productView;
        }).collect(Collectors.toList()));
        percent.textProperty().addListener(((observableValue, s, t1) -> {
            if (!t1.matches("\\d*(\\.\\d*)?")) {
                percent.setText(s);
            }
            percent.setText(t1);
        }));
    }

    @FXML
    void confirmation() throws IOException {
        if (!listView.getItems().isEmpty()) {
            if (warning.isVisible()) warning.setVisible(false);
            Router.setData(Pages.SELECT_PRODUCT_FOR_PROMOTION_VIEW, selectProductForPromotionViewModel.getPromotion());
            Router.showDialog(Pages.PROMOTION_VIEW);
        } else if (!warning.isVisible()) warning.setVisible(true);
    }

    @FXML
    void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }

    @FXML
    void addAll() {
        if (!percent.getText().isEmpty()) {
            if (warning.isVisible()) warning.setVisible(false);
            productsPane.getChildren().forEach(e -> {
                ProductView productView = (ProductView) e;
                insertProduct(productView);
            });
        } else if (!warning.isVisible()) warning.setVisible(true);
    }

    private void insertProduct(ProductView product) {
        selectProductForPromotionViewModel.setEmptyText(selectProductForPromotionViewModel.getEmptyText() + 1);
        if (!product.isDisable()) {
            selectProductForPromotionViewModel.getPromotion().getProducts().put(product.getProduct().getId(), Double.parseDouble(percent.getText()));
            ProductInPromotion productInPromotion = new ProductInPromotion(product.getProduct(), Double.parseDouble(percent.getText()));
            listView.getItems().add(productInPromotion);
            productInPromotion.getButton().setOnAction(event -> removeFromListView(product, productInPromotion));
            productInPromotion.getTextField().textProperty().addListener(((observableValue, s, t1) -> {
                if (!t1.matches("\\d*(\\.\\d*)?")) {
                    productInPromotion.getTextField().setText(s);
                }
                if (!productInPromotion.getTextField().getText().isEmpty())
                    selectProductForPromotionViewModel.getPromotion().getProducts().put(product.getProduct().getId(), Double.parseDouble(productInPromotion.getTextField().getText()));
            }));

            product.setDisable(true);
        }
    }

    private void removeFromListView(ProductView productView, ProductInPromotion productInPromotion) {
        productView.setDisable(false);
        listView.getItems().remove(productInPromotion);
    }
}
