package com.example.project_client.view.controller.Khai;

import com.example.project_client.model.SalaryCal;
import com.example.project_client.model.TimeAndNameRequest;
import com.example.project_client.model.TimeRequest;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Khai.Function.FunctionKhai;
import com.example.project_client.viewModel.Khai.SalaryCalViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalaryCalViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentRoot.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                tableSalary.getSelectionModel().clearSelection();
            }
        });
        idStaffTf.setFocusTraversable(false);
        idStaffColumn.setCellValueFactory(new PropertyValueFactory<SalaryCal, String>("id"));
        nameStaffColumn.setCellValueFactory(new PropertyValueFactory<SalaryCal, String>("name"));
        salaryStaffColumn.setCellValueFactory(new PropertyValueFactory<SalaryCal, Integer>("salary"));
        FunctionKhai.validColumnMoney(salaryStaffColumn);
        FunctionKhai.validDatePicker(datePickStart);
        FunctionKhai.validDatePicker(datePickEnd);
    }
    @FXML
    public void salaryCal(ActionEvent event) throws Exception {
        parentRoot.requestFocus();
        if(datePickStart.getValue().isAfter(datePickEnd.getValue())){
            tableSalary.setVisible(false);
            totalSalaryLabel.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ngày bắt đầu không được phép lớn hơn ngày kết thúc!");
            alert.showAndWait();
        }
        try {
            ScrollBar vScrollBar = (ScrollBar) tableSalary.lookup(".scroll-bar:vertical");
            vScrollBar.setValue(vScrollBar.getMin());
            if(idStaffTf.getText() == null || idStaffTf.getText().trim().isEmpty()){
                tableSalary.setItems(FXCollections.observableList(salaryCalViewModel.getAllSalary(new TimeRequest(datePickStart.getValue(), datePickEnd.getValue()))));
                idStaffTf.setText("");
            }else{
                tableSalary.setItems(FXCollections.observableList(salaryCalViewModel.getBySearching(new TimeAndNameRequest(idStaffTf.getText(), datePickStart.getValue(), datePickEnd.getValue()))));
            }

            tableSalary.setVisible(true);
            int totalSalary = 0;
            for(SalaryCal sCD : tableSalary.getItems()) {
                totalSalary += sCD.getSalary();
            }
            if(idStaffTf.getText() == null || idStaffTf.getText().trim().isEmpty()) {
                totalSalaryLabel.setText("Tổng tiền: " + FunctionKhai.convertMoney(totalSalary));
                totalSalaryLabel.setVisible(true);
            }else {
                totalSalaryLabel.setVisible(false);
            }
        } catch (Exception e) {
            tableSalary.setVisible(false);
            totalSalaryLabel.setVisible(false);
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
    private SalaryCalViewModel salaryCalViewModel = new SalaryCalViewModel();
    @FXML
    private AnchorPane parentRoot;

    @FXML
    private TextField idStaffTf;

    @FXML
    private DatePicker datePickStart;

    @FXML
    private DatePicker datePickEnd;

    @FXML
    private TableView<SalaryCal> tableSalary;

    @FXML
    private TableColumn<SalaryCal, String> idStaffColumn;

    @FXML
    private TableColumn<SalaryCal, String> nameStaffColumn;

    @FXML
    private TableColumn<SalaryCal, Integer> salaryStaffColumn;

    @FXML
    private Button salaryCalBtn;

    @FXML
    private Label totalSalaryLabel;

}
