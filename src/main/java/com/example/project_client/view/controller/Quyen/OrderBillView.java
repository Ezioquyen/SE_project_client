package com.example.project_client.view.controller.Quyen;


import com.example.project_client.model.Product;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.viewModel.Quyen.CreateOrderViewModel;
import com.example.project_client.viewModel.Quyen.OrderBillViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.controlsfx.control.GridCell;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    Label total;
    @FXML
    Label received;
    @FXML
    Label change;
    @FXML
    Label method;
    @FXML
    GridPane productContainer;
    private final OrderBillViewModel orderBillViewModel = new OrderBillViewModel();
    @FXML
    void initialize() throws Exception {
        orderBillViewModel.initData();
        buyDate.setText(orderBillViewModel.getData().get("buyDate").toString());
        customerPhone.setText(orderBillViewModel.getData().get("customerPhone").toString());
        billCode.setText(orderBillViewModel.getData().get("id").toString());
        staffId.setText(orderBillViewModel.getData().get("userStaffId").toString());
        total.setText(orderBillViewModel.getData().get("total") + " VND");
        method.setText("Khách trả (" + ((Boolean)orderBillViewModel.getData().get("payMethod")?"Quét mã QR":"Tiền mặt") +"):");
        received.setText(orderBillViewModel.getData().get("received")+ " VND");
        change.setText(orderBillViewModel.getData().get("changeMoney")+ " VND");
        int row = 1;
        for(Map.Entry<Product, SimpleIntegerProperty> entry : ((CreateOrderViewModel)Router.getData(Pages.CREATE_ORDER_VIEW)).getCount().entrySet()){
           Label productName = new Label(entry.getKey().getName());
            productName.setWrapText(true);
            RowConstraints rowConstraints = new RowConstraints(40,30,USE_COMPUTED_SIZE);
            productContainer.getRowConstraints().add(rowConstraints);
            productContainer.add(productName,0,row);
            productContainer.add(new Label(NumberFormat.getNumberInstance(Locale.US).format(entry.getKey().getPrice())),1,row);
            productContainer.add(new Label(entry.getValue().getValue().toString()),2,row);
            productContainer.add(new Label(NumberFormat.getNumberInstance(Locale.US).format( entry.getValue().longValue() * entry.getKey().getPrice())),3,row);
            row++;
        }
    }
    @FXML
    public void done() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
}
