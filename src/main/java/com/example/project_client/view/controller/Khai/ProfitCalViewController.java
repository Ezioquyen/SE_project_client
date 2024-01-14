package com.example.project_client.view.controller.Khai;

import atlantafx.base.theme.Styles;
import com.example.project_client.model.NameAndCount;
import com.example.project_client.model.TimeRequest;
import com.example.project_client.view.controller.Khai.Function.FunctionKhai;
import com.example.project_client.viewModel.Khai.ProfitCalViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        FunctionKhai.addBtnReturn(returnHbox);
        Button salaryCalBtn = new Button("Tính");
        salaryCalBtn.getStyleClass().addAll(Styles.BUTTON_OUTLINED, Styles.SUCCESS, Styles.LARGE);
        salaryCalBtn.setPrefWidth(100);
        salaryCalBtn.setOnAction(e -> {
            try {
                profitCal();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        inputHbox.getChildren().add(salaryCalBtn);
        initStyles();
        profitLinechart.setLegendVisible(false);
    }
    @FXML
    private HBox returnHbox;
    @FXML
    private HBox inputHbox;
    @FXML
    private Label dateStartLabel;
    @FXML
    private DatePicker datePickStart;
    @FXML
    private Label dateEndLabel;
    @FXML
    private DatePicker datePickEnd;
    @FXML
    private Label typeCbbLabel;
    @FXML
    private ComboBox<String> typeCbb;
    @FXML
    private VBox parentTotalVbox;
    @FXML
    private Label sumProfitLabel;
    @FXML
    private Label totalProfitLabel;
    @FXML
    private LineChart<String, Number> profitLinechart;
    @FXML
    private CategoryAxis xAxisProfitLine;
    @FXML
    private NumberAxis yAxisProfitLine;
    private final ProfitCalViewModel profitCalViewModel = new ProfitCalViewModel();
    private void settingOutTime(Integer totalProfit){
        sumProfitLabel.setText("Không thể thống kê!");
        totalProfitLabel.setText("Tổng: " + FunctionKhai.convertMoney(totalProfit));
        yAxisProfitLine.setUpperBound(500000);
        yAxisProfitLine.setLowerBound(0);
        yAxisProfitLine.setTickUnit(50000);
        profitLinechart.getData().clear();
    }
    private void popAlert(int type){
        profitLinechart.setVisible(false);
        parentTotalVbox.setVisible(false);
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
        if(datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.DAYS) >= 40) {
            settingOutTime(totalProfit);
        }else {
            sumProfitLabel.setText("Tổng lợi nhuận: ");
            totalProfitLabel.setText(FunctionKhai.convertMoney(totalProfit));
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            profitLinechart.setData(FXCollections.observableArrayList(series));
            int i, j1 = 0, j2 = 0, j3 = 0, sz = (int) datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.DAYS), min = 0, max = 0;
            LocalDate st = datePickStart.getValue(), en = datePickEnd.getValue();
            series.setName("Thống kê lợi nhuận từ ngày " + FunctionKhai.convertDate(st.toString()) + " tới ngày " + FunctionKhai.convertDate(en.toString()));

            for (i = 0; i <= sz; i++) {
                int total = 0;
                if (j1 < billProducts.size() && st.plusDays(i).toString().equals(billProducts.get(j1).getName())) {
                    total += billProducts.get(j1).getCount();
                    j1++;
                }
                if (j2 < salarys.size() && st.plusDays(i).toString().equals(salarys.get(j2).getName())) {
                    total -= salarys.get(j2).getCount();
                    j2++;
                }
                if (j3 < billIngredients.size() && st.plusDays(i).toString().equals(billIngredients.get(j3).getName())) {
                    total -= billIngredients.get(j3).getCount();
                    j3++;
                }
                if (min > total) min = total;
                if (max < total) max = total;
                XYChart.Data<String, Number> data = new XYChart.Data<>(FunctionKhai.convertDate(st.plusDays(i).toString()), total);
                series.getData().add(data);
            }
            if (max == 0 && min == 0) {
                yAxisProfitLine.setUpperBound(250000);
                yAxisProfitLine.setLowerBound(-250000);
                yAxisProfitLine.setTickUnit(50000);
            } else {
                yAxisProfitLine.setUpperBound(max);
                yAxisProfitLine.setLowerBound(min);
                int diff = (max - min) / 10;
                yAxisProfitLine.setTickUnit(diff);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private void handlePerMonth(TimeRequest timeRequest) throws Exception{
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
        if(datePickStart.getValue().until(datePickEnd.getValue(), ChronoUnit.MONTHS) >= 40) {
            settingOutTime(totalProfit);
        }else{
            totalProfitLabel.setText("Tổng lợi nhuận: " + FunctionKhai.convertMoney(totalProfit));
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            profitLinechart.setData(FXCollections.observableArrayList(series));
            LocalDate st = datePickStart.getValue(), en = datePickEnd.getValue();
            int i, j1 = 0, j2 = 0, j3 = 0, sz = (en.getYear() - st.getYear()) * 12 + en.getMonthValue() - st.getMonthValue() , min = 0, max = 0;
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
                XYChart.Data<String, Number> data = new XYChart.Data<>(FunctionKhai.convertDatePerMonth(st.plusMonths(i)), total);
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
    private void initStyles(){
        FunctionKhai.validLabel(dateStartLabel);
        FunctionKhai.validLabel(dateEndLabel);
        FunctionKhai.validLabel(sumProfitLabel);
        FunctionKhai.validLabel(totalProfitLabel);
        FunctionKhai.validLabel(typeCbbLabel);
        typeCbb.getStyleClass().add(Styles.TITLE_4);
        datePickStart.getStyleClass().add(Styles.TITLE_4);
        datePickEnd.getStyleClass().add(Styles.TITLE_4);
    }
    private void profitCal(){
        if(datePickStart.getValue().isAfter(datePickEnd.getValue())){
            popAlert(1);
            return;
        }
        TimeRequest timeRequest = new TimeRequest(datePickStart.getValue(), datePickEnd.getValue());
        if(typeCbb.getValue().equals("Theo tháng")){
            xAxisProfitLine.setLabel("Tháng");
            try {
                handlePerMonth(timeRequest);
            } catch (Exception e) {
                popAlert(0);
                return;
            }
        }else{
            xAxisProfitLine.setLabel("Ngày");
            try {
                handlePerDay(timeRequest);
            } catch (Exception e) {
                popAlert(0);
                return;
            }
        }
        parentTotalVbox.setVisible(true);
        profitLinechart.setVisible(true);
    }
}