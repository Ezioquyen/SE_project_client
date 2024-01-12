package com.example.project_client.view.controller.Khai;

import com.example.project_client.model.NameAndCount;
import com.example.project_client.model.TimeRequest;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Khai.Function.FunctionKhai;
import com.example.project_client.viewModel.Khai.BillProductViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
public class BillProductCalViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        billProductBarChart.setAnimated(false);
        FunctionKhai.validNumberAxis(yAxistBillProduct);
        billProductLineChart.setAnimated(false);
        FunctionKhai.validNumberAxis(yAxistBillProductLine);
        FunctionKhai.validDatePicker(datePickStart);
        FunctionKhai.validDatePicker(datePickEnd);
        FunctionKhai.validCombobox(typeCbb);
    }

    @SuppressWarnings("unchecked")
    @FXML
    public void billProductCal(ActionEvent event) {
        if(datePickStart.getValue().isAfter(datePickEnd.getValue())){
            popAlert(1);
            return;
        }
        if(typeCbb.getValue().equals("Theo ngày")) {
            xAxistBillProductLine.setLabel("Ngày");
            try {
                handlePerDay();
            } catch (Exception e) {
                popAlert(0);
                return;
            }
        }else{
            xAxistBillProductLine.setLabel("Tháng");
            try {
                handlePerMonth();
            } catch (Exception e) {
                popAlert(0);
                return;
            }
        }
        try {
            int max = -1;
            Series<String, Number> series= new  Series<String, Number>();
            series.setName("Biểu đồ số lượng sản phẩm bán được kể từ: " + FunctionKhai.convertDate(datePickStart.getValue().toString()) + " tới ngày " + FunctionKhai.convertDate(datePickStart.getValue().toString()));
            List<NameAndCount> nameAndCounts = billProductViewModel.getCount(new TimeRequest(datePickStart.getValue(), datePickEnd.getValue()));
            for(NameAndCount n : nameAndCounts) {
                series.getData().add(new  Data<String, Number>(FunctionKhai.convertDate(n.getName()), n.getCount()));
                if(max < n.getCount()) max = n.getCount();
            }
            if(max != -1) {
                yAxistBillProduct.setUpperBound(max + 1);
                if(max + 1 < 10) {
                    yAxistBillProduct.setTickUnit(1);
                }else {
                    max = (max + 1) / 10;
                    yAxistBillProduct.setTickUnit(max);
                }
            }else {
                yAxistBillProduct.setUpperBound(50);
                yAxistBillProduct.setTickUnit(5);
            }
            billProductBarChart.setData(FXCollections.observableArrayList(series));
        } catch (Exception e) {
            popAlert(0);
            return;
        }
        totalProfitLabel.setVisible(true);
        swapChartBtn.setVisible(true);
        billProductBarChart.setVisible(false);
        billProductLineChart.setVisible(true);
        swapChartBtn.setText("Xem thống kê");
    }
    @FXML
    public void returnMainWd(ActionEvent event) throws IOException {
        Router.switchTo(Pages.MAIN_VIEW_PROFIT);
    }
    @FXML
    public void swapChart(ActionEvent event){
        if(swapChartBtn.getText().equals("Xem thống kê")) {
            billProductLineChart.setVisible(false);
            billProductBarChart.setVisible(true);
            swapChartBtn.setText("Xem doanh thu");
        }else {
            billProductBarChart.setVisible(false);
            billProductLineChart.setVisible(true);
            swapChartBtn.setText("Xem thống kê");
        }
    }
    @FXML
    private AnchorPane parentRoot;
    @FXML
    private DatePicker datePickStart;
    @FXML
    private DatePicker datePickEnd;
    @FXML
    private Button billProductCalBtn;
    @FXML
    private BarChart<String, Number> billProductBarChart;
    @FXML
    private CategoryAxis xAxistBillProduct;
    @FXML
    private NumberAxis yAxistBillProduct;
    @FXML
    private LineChart<String, Number> billProductLineChart;
    @FXML
    private Label totalProfitLabel;
    @FXML
    private CategoryAxis xAxistBillProductLine;
    @FXML
    private NumberAxis yAxistBillProductLine;
    @FXML
    private Button returnMainWdBtn;
    @FXML
    private Button swapChartBtn;
    @FXML
    private VBox inforVbox;
    @FXML
    private Label dateInforLabel;
    @FXML
    private Label profitInforLabel;
    @FXML
    private ComboBox<String> typeCbb;
    @FXML
    private BillProductViewModel billProductViewModel = new BillProductViewModel();

    private void settingOutTime(int totalProfit){
        totalProfitLabel.setText("Do số lượng thống kê quá nhiều nên hệ thống sẽ không hiển thị trên biểu đồ. Tổng doanh thu: " + FunctionKhai.convertMoney(totalProfit));
        yAxistBillProductLine.setUpperBound(500000);
        yAxistBillProductLine.setTickUnit(50000);
        billProductLineChart.getData().clear();
    }
    private void popAlert(int type){
        totalProfitLabel.setVisible(false);
        swapChartBtn.setVisible(false);
        billProductBarChart.setVisible(false);
        billProductLineChart.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(type == 1){
            alert.setContentText("Ngày bắt đầu không được lớn hơn ngày kết thúc!");
        }else{
            alert.setContentText("Đã có lỗi xảy ra với máy chủ, vui lòng thử lại sau!");
        }
        alert.showAndWait();
    }
    @SuppressWarnings("unchecked")
    private void handlePerDay() throws Exception {
        List<NameAndCount> nameAndCounts = billProductViewModel.getPerDay(new TimeRequest(datePickStart.getValue(), datePickEnd.getValue()));
        int totalProfit = 0;
        for(NameAndCount n : nameAndCounts){
            totalProfit += n.getCount();
        }if(datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.DAYS) >= 50) {
            settingOutTime(totalProfit);
        }else{
            totalProfitLabel.setText("Tổng doanh thu: " + FunctionKhai.convertMoney(totalProfit));
            Series<String, Number> series = new Series<>();
            billProductLineChart.setData(FXCollections.observableArrayList(series));
            LocalDate st = datePickStart.getValue(), en = datePickEnd.getValue();
            int max = -1, i = 0, j = 0, sz = (int) st.until(en, ChronoUnit.DAYS);
            series.setName("Thống kê doanh thu từ ngày " + FunctionKhai.convertDate(st.toString()) + " tới ngày " + FunctionKhai.convertDate(en.toString()));
            for(i = 0; i <= sz; i++) {
                int total = 0;
                if(j < nameAndCounts.size() && st.plusDays(i).toString().equals(nameAndCounts.get(j).getName())){
                    total += nameAndCounts.get(j).getCount();
                    j++;
                }
                Data<String, Number> data = new Data<>(FunctionKhai.convertDate(st.plusDays(i).toString()), total);
                series.getData().add(data);
                data.getNode().setOnMouseEntered(e -> {
                    dateInforLabel.setText("Date: " + data.getXValue());
                    profitInforLabel.setText("Revenue: " + FunctionKhai.convertMoney((Integer) data.getYValue()));
                    inforVbox.setVisible(true);
                    inforVbox.setLayoutX(e.getSceneX() - 240);
                    inforVbox.setLayoutY(e.getSceneY() - 55);
                });
                data.getNode().setOnMouseExited(e -> {
                    inforVbox.setVisible(false);
                });
                if(max < total) max = total;
            }
            if(max != -1) {
                max = max + max / 10;
                yAxistBillProductLine.setUpperBound(max);
                max /= 11;
                yAxistBillProductLine.setTickUnit(max);
            }else {
                yAxistBillProductLine.setUpperBound(500000);
                yAxistBillProductLine.setTickUnit(50000);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private void handlePerMonth() throws Exception {
        List<NameAndCount> nameAndCounts = billProductViewModel.getPerMonth(new TimeRequest(datePickStart.getValue(), datePickEnd.getValue()));
        int totalProfit = 0;
        for(NameAndCount n : nameAndCounts){
            totalProfit += n.getCount();
        }if(datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.MONTHS) >= 50) {
            settingOutTime(totalProfit);
        }else {
            totalProfitLabel.setText("Tổng doanh thu: " + FunctionKhai.convertMoney(totalProfit));
            Series<String, Number> series = new Series<>();
            billProductLineChart.setData(FXCollections.observableArrayList(series));

            LocalDate st = datePickStart.getValue(), en = datePickEnd.getValue();
            int max = -1, j = 0, difMonth = (en.getYear() - st.getYear()) * 12 + en.getMonthValue() - st.getMonthValue();
            series.setName("Thống kê doanh thu từ tháng " + FunctionKhai.convertDatePerMonth(st) + " tới tháng " + FunctionKhai.convertDatePerMonth(en));
            for(int i = 0; i <= difMonth; i++) {
                int total = 0;
                if(j < nameAndCounts.size() && FunctionKhai.convertDatePerMonth(st.plusMonths(i)).equals(FunctionKhai.convertDate(nameAndCounts.get(j).getName()))) {
                    total += nameAndCounts.get(j).getCount();
                    j++;
                }
                Data<String, Number> data = new Data<>(FunctionKhai.convertDatePerMonth(st.plusMonths(i)), total);
                series.getData().add(data);
                data.getNode().setOnMouseEntered(e -> {
                    dateInforLabel.setText("Date: " + data.getXValue());
                    profitInforLabel.setText("Revenue: " + FunctionKhai.convertMoney((Integer) data.getYValue()));
                    inforVbox.setVisible(true);
                    inforVbox.setLayoutX(e.getSceneX() - 240);
                    inforVbox.setLayoutY(e.getSceneY() - 55);
                });
                data.getNode().setOnMouseExited(e -> {
                    inforVbox.setVisible(false);
                });
                if(max < total) max = total;
            }
            if(max != -1) {
                max = max + max / 10;
                yAxistBillProductLine.setUpperBound(max);
                max /= 11;
                yAxistBillProductLine.setTickUnit(max);
            }else {
                yAxistBillProductLine.setUpperBound(500000);
                yAxistBillProductLine.setTickUnit(50000);
            }
        }
    }
}
