package com.example.project_client.view.controller.Quyen;


import com.example.project_client.router.Router;
import com.example.project_client.viewModel.Quyen.ConfirmationViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConfirmationView {
    @FXML
    TextField  phoneNumber;
    @FXML
    TextField customerName;
    @FXML
    TextField money;
    private final ConfirmationViewModel confirmationViewModel = new ConfirmationViewModel();
    @FXML
    public void initialize(){
        phoneNumber.textProperty().addListener((e,oldVal,newVal)->{
            if (!newVal.matches("\\d*")) {
                phoneNumber.setText(newVal.replaceAll("\\D", ""));
            }
            confirmationViewModel.getCustomer().setPhoneNumber(phoneNumber.getText());
            try {
                if(confirmationViewModel.checkCustomer()){
                    confirmationViewModel.findCustomer();
                    customerName.setText(confirmationViewModel.getCustomer().getName());
                    customerName.setDisable(true);
                } else {
                    System.out.println("customer is not exist");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        money.textProperty().addListener(((observableValue, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                money.setText(newVal.replaceAll("\\D", ""));
            }
        }));
    }
    @FXML
    public void close(){
        Router.closeDialog();
    }

}
