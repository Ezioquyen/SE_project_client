package com.example.project_client.view.controller.Quyen;


import com.example.project_client.model.Product;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.viewModel.Quyen.CreateOrderViewModel;
import com.example.project_client.viewModel.Quyen.OrderBillViewModel;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.text.NumberFormat;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class OrderBillView {
    @FXML
    Label buyDate;
    @FXML
    Label customerPhone;
    @FXML
    Label billCode;
    @FXML
    Label staffId;
    @FXML
    Label deduction;
    @FXML
    Label original;
    @FXML
    Label total;
    @FXML
    Label received;
    @FXML
    Label change;
    @FXML
    Label method;
    @FXML
    TableView<TableRow> productsContainer;
    @FXML
    TableColumn<TableRow, String> name;
    @FXML
    TableColumn<TableRow, String> price;
    @FXML
    TableColumn<TableRow, Integer> count;
    @FXML
    TableColumn<TableRow, ImageView> image;
    @FXML
    TableColumn<TableRow, String> totalPrice;
    private final OrderBillViewModel orderBillViewModel = new OrderBillViewModel();

    @FXML
    void initialize() throws Exception {
        name.setCellValueFactory(cellData -> cellData.getValue().getProductName());
        price.setCellValueFactory(cellData -> cellData.getValue().getPrice());
        image.setCellValueFactory(cellData -> cellData.getValue().getImageView());
        count.setCellValueFactory(cellData -> cellData.getValue().getCount().asObject());
        totalPrice.setCellValueFactory(cellData -> cellData.getValue().getTotal());
        orderBillViewModel.initData(((CreateOrderViewModel) Router.getData(Pages.CREATE_ORDER_VIEW)).getOrderBill());
        buyDate.setText(orderBillViewModel.getData().getBuyDate());
        customerPhone.setText(orderBillViewModel.getData().getCustomerPhoneNumber());
        billCode.setText(orderBillViewModel.getData().getId());
        staffId.setText(orderBillViewModel.getData().getUserStaffId());
        total.setText(NumberFormat.getNumberInstance(Locale.US).format(orderBillViewModel.getData().getTotal()) + " VND");
        method.setText("Khách trả (" + (orderBillViewModel.getData().getPayMethod() ? "Quét mã QR" : "Tiền mặt") + "):");
        received.setText(NumberFormat.getNumberInstance(Locale.US).format(orderBillViewModel.getData().getReceived()) + " VND");
        change.setText(NumberFormat.getNumberInstance(Locale.US).format(orderBillViewModel.getData().getChangeMoney()) + " VND");
        deduction.setText("- " + NumberFormat.getNumberInstance(Locale.US).format(orderBillViewModel.getData().getDeduction()) + " VND");
        original.setText(NumberFormat.getNumberInstance(Locale.US).format(orderBillViewModel.getData().getOriginal()) + " VND");

        ((CreateOrderViewModel) Router.getData(Pages.CREATE_ORDER_VIEW)).getCount().forEach((product, simpleIntegerProperty) -> {
            productsContainer.getItems().add(new TableRow(
                    product.getName(),
                    product.getImage(),
                    NumberFormat.getNumberInstance(Locale.US).format(product.getPrice()),
                    simpleIntegerProperty.intValue(),
                    NumberFormat.getNumberInstance(Locale.US).format(simpleIntegerProperty.longValue() * product.getPrice()))
            );
        });


    }

    @FXML
    public void done() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
}

@Getter
@Setter
class TableRow {
    private final StringProperty productName;
    private final StringProperty price;
    private final ObjectProperty<ImageView> imageView;
    private final IntegerProperty count;
    private final StringProperty total;

    public TableRow(String productName, String imageUrl, String price, Integer count, String total) {
        this.productName = new SimpleStringProperty(productName);
        this.price = new SimpleStringProperty(price);
        this.count = new SimpleIntegerProperty(count);
        this.total = new SimpleStringProperty(total);
        this.imageView = new SimpleObjectProperty<>(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageUrl)))));
        imageView.get().setFitHeight(150.0);
        imageView.get().setFitWidth(150.0);
    }
}
