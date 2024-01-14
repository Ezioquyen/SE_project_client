package com.example.project_client.view.controller.Quyen;

import com.example.project_client.model.Promotion;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.ProductInPromotion;
import com.example.project_client.view.controller.Quyen.components.ProductView;
import com.example.project_client.viewModel.Quyen.PromotionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.stream.Collectors;

public class PromotionView {
    @FXML
    ListView<ProductInPromotion> listView;

    @FXML
    TextField percent;
    @FXML
    FlowPane productsPane;
    @FXML
    Button addAll;

    @FXML
    Button remove;
    private Boolean isWarn = false;
    @FXML
    TextField name;
    @FXML
    TextArea information;
    @FXML
    DatePicker startTime;
    @FXML
    DatePicker endTime;
    @FXML
    CheckBox condition;
    @FXML
    VBox warningVBox;
    @FXML
    VBox parent;
    private final PromotionViewModel promotionViewModel = new PromotionViewModel();

    @FXML
    void initialize() throws IOException {
        parent.getChildren().remove(warningVBox);
        promotionViewModel.initData((Promotion) Router.getData(Pages.MAIN_VIEW));
        remove.setVisible(!promotionViewModel.getIsCreate());
        productsPane.getChildren().addAll(promotionViewModel.getProducts().stream().map(e -> {
            ProductView productView = new ProductView(e);
            productView.setOnMouseClicked(mouseEvent -> insertProduct(productView, Double.parseDouble(percent.getText())));
            if (promotionViewModel.getPromotion().getProducts().get(e.getId()) != null) {
                insertProduct(productView, promotionViewModel.getPromotion().getProducts().get(e.getId()));
            }
            return productView;
        }).collect(Collectors.toList()));
        percent.textProperty().addListener(((observableValue, s, t1) -> {
            if (!t1.matches("\\d*(\\.\\d*)?")) {
                percent.setText(s);
            }
            percent.setText(t1);
        }));

        name.setText(promotionViewModel.getPromotion().getName());
        information.setText(promotionViewModel.getPromotion().getInformation());
        startTime.setValue(promotionViewModel.getPromotion().getStartDate());
        endTime.setValue(promotionViewModel.getPromotion().getEndDate());
        condition.setSelected(promotionViewModel.getPromotion().getNeedCondition());
        name.textProperty().addListener((observableValue, s, t1) -> promotionViewModel.getPromotion().setName(t1));
        information.textProperty().addListener(((observableValue, s, t1) -> promotionViewModel.getPromotion().setInformation(t1)));
        startTime.valueProperty().addListener((observableValue, date, t1) -> promotionViewModel.getPromotion().setStartDate(t1));
        endTime.valueProperty().addListener(((observableValue, date, t1) -> promotionViewModel.getPromotion().setEndDate(t1)));
        condition.selectedProperty().addListener((observableValue, aBoolean, t1) -> promotionViewModel.getPromotion().setNeedCondition(t1));
    }

    @FXML
    void confirmation() throws Exception {


        if (showWarning()) {
            promotionViewModel.createPromotion();
            Router.switchTo(Pages.MAIN_VIEW);
        }

    }

    @FXML
    void cancel() throws IOException {
        Router.removeData(Pages.MAIN_VIEW);
        Router.switchTo(Pages.MAIN_VIEW);
    }

    @FXML
    void addAll() {
        if (!percent.getText().isEmpty()) {

            productsPane.getChildren().forEach(e -> {
                ProductView productView = (ProductView) e;
                insertProduct(productView, percent.getText().isEmpty() ? 10.0 : Double.parseDouble(percent.getText()));
            });
        }
    }

    private void insertProduct(ProductView product, Double percent) {
        promotionViewModel.setEmptyText(promotionViewModel.getEmptyText() + 1);
        if (!product.isDisable()) {
            promotionViewModel.getPromotion().getProducts().put(product.getProduct().getId(), percent);
            ProductInPromotion productInPromotion = new ProductInPromotion(product.getProduct(), percent);
            listView.getItems().add(productInPromotion);
            productInPromotion.getButton().setOnAction(event -> removeFromListView(product, productInPromotion));
            productInPromotion.getTextField().textProperty().addListener(((observableValue, s, t1) -> {
                if (!t1.matches("\\d*(\\.\\d*)?")) {
                    productInPromotion.getTextField().setText(s);
                }
                if (!productInPromotion.getTextField().getText().isEmpty())
                    promotionViewModel.getPromotion().getProducts().put(product.getProduct().getId(), Double.parseDouble(productInPromotion.getTextField().getText()));
                else
                    promotionViewModel.getPromotion().getProducts().put(product.getProduct().getId(), percent);
            }));

            product.setDisable(true);
        }
    }

    private void removeFromListView(ProductView productView, ProductInPromotion productInPromotion) {
        productView.setDisable(false);
        listView.getItems().remove(productInPromotion);
    }

    @FXML
    public void remove() throws IOException {
        promotionViewModel.removePromo();
        Router.switchTo(Pages.MAIN_VIEW);
    }

    private Boolean showWarning() throws IOException {
        if (name.getText().isEmpty()
                || information.getText().isEmpty()
                || startTime.getValue().isAfter(endTime.getValue())
                || !promotionViewModel.check(startTime.getValue())
                || listView.getItems().isEmpty()) {
            if (!isWarn) {
                parent.getChildren().add(warningVBox);
                isWarn = true;
            }
            return false;
        }
        return true;
    }
}
