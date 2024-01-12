package com.example.project_client.view.controller.Khai;

import com.example.project_client.model.NameAndCount;
import com.example.project_client.model.TimeRequest;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Khai.Function.FunctionKhai;
import com.example.project_client.viewModel.Khai.ProfitCalViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class ProfitCalViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profitLinechart.setAnimated(false);
        FunctionKhai.validDatePicker(datePickEnd);
        FunctionKhai.validDatePicker(datePickStart);
        FunctionKhai.validNumberAxis(yAxisProfitLine);
        FunctionKhai.validCombobox(typeCbb);
    }
    @FXML
    public void profitCal(ActionEvent event){
        if(datePickStart.getValue().isAfter(datePickEnd.getValue())){
            popAlert(1);
            return;
        }
        TimeRequest timeRequest = new TimeRequest(datePickStart.getValue(), datePickEnd.getValue());
        if(typeCbb.getValue().equals("Theo tháng")){
            try {
                handlePerMonth(timeRequest);
            } catch (Exception e) {
                popAlert(0);
                System.err.println(e);
                return;
            }
        }else{
            try {
                handlePerDay(timeRequest);
            } catch (Exception e) {
                popAlert(0);
                System.err.println(e);
                return;
            }
        }
        totalProfitLabel.setVisible(true);
        profitLinechart.setVisible(true);
    }
    @FXML
    public void returnMainView(ActionEvent event) throws IOException {
        Router.switchTo(Pages.MAIN_VIEW_PROFIT);
    }
    @FXML
    private Button returnMainViewBtn;
    @FXML
    private DatePicker datePickStart;
    @FXML
    private DatePicker datePickEnd;
    @FXML
    private Button profitCalBtn;
    @FXML
    private LineChart<String, Number> profitLinechart;
    @FXML
    private CategoryAxis xAxisProfitLine;
    @FXML
    private NumberAxis yAxisProfitLine;
    @FXML
    private ComboBox<String> typeCbb;
    @FXML
    private Label totalProfitLabel;
    @FXML
    private ProfitCalViewModel profitCalViewModel = new ProfitCalViewModel();
    private void settingOutTime(Integer totalProfit){
        totalProfitLabel.setText("Do số lượng thống kê quá nhiều nên hệ thống sẽ không hiển thị trên biểu đồ. Tổng lợi nhuận: " + FunctionKhai.convertMoney(totalProfit));
        yAxisProfitLine.setUpperBound(500000);
        yAxisProfitLine.setLowerBound(0);
        yAxisProfitLine.setTickUnit(50000);
        profitLinechart.getData().clear();
    }
    private void popAlert(int type){
        profitLinechart.setVisible(false);
        totalProfitLabel.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(type == 1){
            alert.setContentText("Ngày bắt đầu không được phép lớn hơn ngày kết thúc!");
        }else{
            alert.setContentText("Đã có lỗi xảy ra với máy chủ, vui lòng thử lại sau!");
        }
        alert.showAndWait();
    }
    @SuppressWarnings("unchecked")
    private void handlePerDay(TimeRequest timeRequest) throws Exception {
        List<NameAndCount> billProducts = profitCalViewModel.getBillProductViewModel().getPerDay(timeRequest);
        List<NameAndCount> billIngredients = profitCalViewModel.getBillIngredientCalViewModel().getPerDay(timeRequest);
        List<NameAndCount> salarys = profitCalViewModel.getSalaryCalViewModel().getPerDay(timeRequest);
        int totalProfit = 0;
        for(NameAndCount b : billProducts) {
            totalProfit += b.getCount();
        }
        for(NameAndCount b : salarys) {
            totalProfit -= b.getCount();
        }
        for(NameAndCount b : billIngredients) {
            totalProfit -= b.getCount();
        }
        if(datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.DAYS) >= 50) {
            settingOutTime(totalProfit);
        }else{
            totalProfitLabel.setText("Tổng lợi nhuận: " + FunctionKhai.convertMoney(totalProfit));
            Series<String, Number> series = new Series<>();
            profitLinechart.setData(FXCollections.observableArrayList(series));
            int i = 0, j1 = 0, j2 = 0, j3 = 0, sz = (int) datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.DAYS), min = 0, max = 0;
            LocalDate st = datePickStart.getValue(), en = datePickEnd.getValue();
            series.setName("Thống kê lợi nhuận từ ngày " + FunctionKhai.convertDate(st.toString()) + " tới ngày " + FunctionKhai.convertDate(en.toString()));

            for(i = 0; i <= sz; i++) {
                int total = 0;
                if(j1 < billProducts.size() && st.plusDays(i).toString().equals(billProducts.get(j1).getName())) {
                    total += billProducts.get(j1).getCount();
                    j1++;
                }
                if(j2 < salarys.size() && st.plusDays(i).toString().equals(salarys.get(j2).getName())) {
                    total -= salarys.get(j2).getCount();
                    j2++;
                }
                if(j3 < billIngredients.size() && st.plusDays(i).toString().equals(billIngredients.get(j3).getName())) {
                    total -= billIngredients.get(j3).getCount();
                    j3++;
                }
                if(min > total) min = total;
                if(max < total) max = total;
                Data<String, Number> data = new Data<>(FunctionKhai.convertDate(st.plusDays(i).toString()), total);
                series.getData().add(data);
            }
            if(max == 0 && min == 0) {
                yAxisProfitLine.setUpperBound(250000);
                yAxisProfitLine.setLowerBound(-250000);
                yAxisProfitLine.setTickUnit(50000);
            }else {
                yAxisProfitLine.setUpperBound(max);
                yAxisProfitLine.setLowerBound(min);
                int diff = (max - min) / 10;
                yAxisProfitLine.setTickUnit(diff);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private void handlePerMonth(TimeRequest timeRequest) throws Exception {
        List<NameAndCount> billProducts = profitCalViewModel.getBillProductViewModel().getPerMonth(timeRequest);
        List<NameAndCount> billIngredients = profitCalViewModel.getBillIngredientCalViewModel().getPerMonth(timeRequest);
        List<NameAndCount> salarys = profitCalViewModel.getSalaryCalViewModel().getPerMonth(timeRequest);
        int totalProfit = 0;
        for(NameAndCount b : billProducts) {
            totalProfit += b.getCount();
        }
        for(NameAndCount b : salarys) {
            totalProfit -= b.getCount();
        }
        for(NameAndCount b : billIngredients) {
            totalProfit -= b.getCount();
        }
        if(datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.MONTHS) >= 50) {
            settingOutTime(totalProfit);
        }else{
            totalProfitLabel.setText("Tổng lợi nhuận: " + FunctionKhai.convertMoney(totalProfit));
            Series<String, Number> series = new Series<>();
            profitLinechart.setData(FXCollections.observableArrayList(series));
            LocalDate st = datePickStart.getValue(), en = datePickEnd.getValue();
            int i = 0, j1 = 0, j2 = 0, j3 = 0, sz = (en.getYear() - st.getYear()) * 12 + en.getMonthValue() - st.getMonthValue() , min = 0, max = 0;
            series.setName("Thống kê lợi nhuận từ tháng " + FunctionKhai.convertDatePerMonth(st) + " tới tháng " + FunctionKhai.convertDatePerMonth(en));

            for(i = 0; i <= sz; i++) {
                int total = 0;
                if(j1 < billProducts.size() && FunctionKhai.convertDatePerMonth(st.plusMonths(i)).equals(FunctionKhai.convertDate(billProducts.get(j1).getName()))) {
                    total += billProducts.get(j1).getCount();
                    j1++;
                }
                if(j2 < salarys.size() && FunctionKhai.convertDatePerMonth(st.plusMonths(i)).equals(FunctionKhai.convertDate(salarys.get(j2).getName()))) {
                    total -= salarys.get(j2).getCount();
                    j2++;
                }
                if(j3 < billIngredients.size() && FunctionKhai.convertDatePerMonth(st.plusMonths(i)).equals(FunctionKhai.convertDate(billIngredients.get(j3).getName()))) {
                    total -= billIngredients.get(j3).getCount();
                    j3++;
                }
                if(min > total) min = total;
                if(max < total) max = total;
                Data<String, Number> data = new Data<>(FunctionKhai.convertDatePerMonth(st.plusMonths(i)), total);
                series.getData().add(data);
            }
            if(max == 0 && min == 0) {
                yAxisProfitLine.setUpperBound(250000);
                yAxisProfitLine.setLowerBound(-250000);
                yAxisProfitLine.setTickUnit(50000);
            }else {
                yAxisProfitLine.setUpperBound(max);
                yAxisProfitLine.setLowerBound(min);
                int diff = (max - min) / 10;
                yAxisProfitLine.setTickUnit(diff);
            }
        }
    }
}