package com.example.project_client.view.controller.Khai.Function;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FunctionKhai {

    private static final NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
    public static void validDatePicker(DatePicker datePicker){
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(LocalDate.now()));
                    }});
        datePicker.setValue(LocalDate.now());
    }

    public static <T> void validColumnMoney(TableColumn<T, Integer> tableColumn){
        tableColumn.setCellFactory(salaryCalIntegerTableColumn -> new TableCell<T, Integer>(){
            @Override
            public void updateItem(Integer salary, boolean empty) {
                super.updateItem(salary, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(FunctionKhai.convertMoney(salary));
                }
            }
        });
    }

    public static void validNumberAxis(NumberAxis numberAxis){
        numberAxis.setAutoRanging(false);
        numberAxis.setMinorTickCount(1);
    }

    public static String convertDate(String date){
        String[] v = date.split("-");
        String result = v[v.length - 1];
        for(int i = v.length - 2; i >= 0; i--) {
            result += "-" + v[i];
        }
        return result;
    }

    public static void validCombobox(ComboBox<String> comboBox){
        comboBox.getItems().addAll("Theo ngày", "Theo tháng");
        comboBox.setValue("Theo ngày");
    }

    public static String convertDatePerMonth(LocalDate localDate){
        return localDate.format(formatter);
    }
    public static String convertMoney(Integer money){
        if(money > 0){
            String result = us.format(money);
            return result.substring(1, result.length() - 3) + " đồng";
        }
        if(money < 0){
            String result = us.format(-money);
            return "-" + result.substring(1, result.length() - 3) + " đồng";
        }
        return "0 đồng";
    }
}
