package com.example.project_client.view.controller.Khai;

import com.example.project_client.model.BillIngredientCal;
import com.example.project_client.model.TimeRequest;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Khai.Function.FunctionKhai;
import com.example.project_client.viewModel.Khai.BillIngredientCalViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BillIngredientCalViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameIngeredientColumn.setCellValueFactory(new PropertyValueFactory<BillIngredientCal, String>("name"));
        countIngeredientColumn.setCellValueFactory(new PropertyValueFactory<BillIngredientCal, Integer>("count"));
        unitIngeredientColumn.setCellValueFactory(new PropertyValueFactory<BillIngredientCal, String>("unit"));
        totalIngeredientColumn.setCellValueFactory(new PropertyValueFactory<BillIngredientCal, Integer>("total"));
        FunctionKhai.validColumnMoney(totalIngeredientColumn);
        FunctionKhai.validDatePicker(datePickStart);
        FunctionKhai.validDatePicker(datePickEnd);
    }

    @FXML
    public void billIngredientCal(ActionEvent event) throws Exception {
        if(datePickStart.getValue().isAfter(datePickEnd.getValue())){
            tableIngredient.setVisible(false);
            totalBillIngredientLabel.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ngày bắt đầu không được phép lớn hơn ngày kết thúc!");
            alert.showAndWait();
        }
        try {
            ScrollBar vScrollBar = (ScrollBar) tableIngredient.lookup(".scroll-bar:vertical");
            vScrollBar.setValue(vScrollBar.getMin());
            tableIngredient.setItems(FXCollections.observableList(billIngredientCalViewModel.getCount(new TimeRequest(datePickStart.getValue(), datePickEnd.getValue()))));
            tableIngredient.setVisible(true);
            int totalBillIngredient = 0;
            for(BillIngredientCal b : tableIngredient.getItems()) {
                totalBillIngredient += b.getTotal();
            }
            totalBillIngredientLabel.setText("Tổng tiền mua nguyên liệu: " + FunctionKhai.convertMoney(totalBillIngredient));
            totalBillIngredientLabel.setVisible(true);
        } catch (Exception e) {
            tableIngredient.setVisible(false);
            totalBillIngredientLabel.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã có lỗi xảy ra, vui lòng thử lại sau!");
            alert.showAndWait();
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void returnMainView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.MAIN_VIEW_PROFIT);
    }
    @FXML
    private DatePicker datePickStart;
    @FXML
    private DatePicker datePickEnd;
    @FXML
    private Button billIngredientCalBtn;
    @FXML
    private Button returnMainViewBtn;
    @FXML
    private Label totalBillIngredientLabel;
    @FXML
    private TableView<BillIngredientCal> tableIngredient;
    @FXML
    private TableColumn<BillIngredientCal, String> nameIngeredientColumn;
    @FXML
    private TableColumn<BillIngredientCal, Integer> countIngeredientColumn;
    @FXML
    private TableColumn<BillIngredientCal, String> unitIngeredientColumn;
    @FXML
    private TableColumn<BillIngredientCal, Integer> totalIngeredientColumn;
    @FXML
    private BillIngredientCalViewModel billIngredientCalViewModel = new BillIngredientCalViewModel();
}
