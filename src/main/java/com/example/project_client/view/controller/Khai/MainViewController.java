package com.example.project_client.view.controller.Khai;

import com.example.project_client.model.*;
import com.example.project_client.repository.BillIngredientCalRepository;
import com.example.project_client.viewModel.Khai.BillIngredientCalViewModel;
import com.example.project_client.viewModel.Khai.SalaryCalViewModel;
import javafx.fxml.Initializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    BillIngredientCalViewModel billIngredientCalViewModel = new BillIngredientCalViewModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Test version 1");
        try {
            System.out.println("Test 01");
            List<BillIngredientCal> salaryCals = billIngredientCalViewModel.getCount(new TimeRequest(LocalDate.of(2023,10,1), LocalDate.of(2024,12,31)));
            for(BillIngredientCal salaryCal : salaryCals){
                System.out.println(salaryCal.getName() + " " + salaryCal.getCount() + " " + salaryCal.getUnit() + " " + salaryCal.getTotal());
            }
        } catch (Exception e) {
            System.out.println("That bai");
        }
        try {
            System.out.println("Test 02");
            List<NameAndCount> salaryCals = billIngredientCalViewModel.getPerDay(new TimeRequest(LocalDate.of(2023,10,1), LocalDate.of(2024,12,31)));
            for(NameAndCount salaryCal : salaryCals){
                System.out.println(salaryCal.getName() + " " + salaryCal.getCount());
            }
        } catch (Exception e) {
            System.out.println("That bai");
        }
        try {
            System.out.println("Test 03");
            List<NameAndCount> salaryCals = billIngredientCalViewModel.getPerMonth(new TimeRequest(LocalDate.of(2023,10,1), LocalDate.of(2024,12,31)));
            for(NameAndCount salaryCal : salaryCals){
                System.out.println(salaryCal.getName() + " " + salaryCal.getCount());
            }
        } catch (Exception e) {
            System.out.println("That bai");
        }
    }
}
