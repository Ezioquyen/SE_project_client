package com.example.project_client.view.controller.Quyen;


import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.viewModel.Quyen.ConfirmationViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class ConfirmationView {
    @FXML
    TextField  phoneNumber;
    @FXML
    TextField customerName;
    @FXML
    TextField money;
    @FXML
    RadioButton qr;
    @FXML
    RadioButton cash;
    @FXML
    Label notification;
    private final ConfirmationViewModel confirmationViewModel = new ConfirmationViewModel();
    @FXML
    public void initialize(){
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(qr,cash);
        qr.setSelected(true);
        cash.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
            confirmationViewModel.setMethod(!t1);
        }));
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
                    if(customerName.isDisable()) {
                        customerName.setDisable(false);
                        customerName.setText("");
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        customerName.textProperty().addListener((obs,oldVal,newVal)->{
            confirmationViewModel.getCustomer().setName(newVal);
        });
        money.textProperty().addListener(((observableValue, oldVal, newVal) -> {
            String val=newVal;
            if (!newVal.matches("\\d*")) {
                val = newVal.replaceAll("\\D", "");
            }
            money.setText(NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(val)));

        }));
        Router.setData(Pages.CONFIRMATION_VIEW,confirmationViewModel);
    }
    @FXML
    public void close(){
        Router.closeDialog();
    }
    @FXML
    public void confirm() throws Exception {
        if(money.getText().isEmpty()){
            if(!notification.isVisible()) notification.setVisible(true);
            return;
        }
        if(!notification.isVisible()) notification.setVisible(false);
        Router.closeDialog();
        if(!confirmationViewModel.getIsCostumerExist()&&!phoneNumber.getText().isEmpty()) confirmationViewModel.saveCustomer();
        confirmationViewModel.getData().put("money", money.getText().replace(",",""));
        confirmationViewModel.getData().put("method",confirmationViewModel.getMethod());
        Router.switchTo(Pages.ORDER_BILL_VIEW);
    }

}
