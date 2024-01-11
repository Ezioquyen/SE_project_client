package com.example.project_client.view.controller.Quyen;


import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.DobFormatter;
import com.example.project_client.viewModel.Quyen.ConfirmationViewModel;
import com.example.project_client.viewModel.Quyen.CreateOrderViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class ConfirmationView {
    @FXML
    TextField phoneNumber;
    @FXML
    TextField customerName;
    @FXML
    TextField money;
    @FXML
    DatePicker dob;
    @FXML
    RadioButton qr;
    @FXML
    RadioButton cash;
    @FXML
    Label notification;
    private final ConfirmationViewModel confirmationViewModel = new ConfirmationViewModel();

    @FXML
    public void initialize() {
        CreateOrderViewModel createOrderViewModel = (CreateOrderViewModel) Router.getData(Pages.CREATE_ORDER_VIEW);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(qr, cash);
        qr.setSelected(true);

        cash.selectedProperty().addListener(((observableValue, aBoolean, t1) -> confirmationViewModel.setMethod(!t1)));
        phoneNumber.textProperty().addListener((e, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                phoneNumber.setText(newVal.replaceAll("\\D", ""));
            }
            confirmationViewModel.getCustomer().setPhoneNumber(phoneNumber.getText());
            try {
                confirmationViewModel.findCustomer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (confirmationViewModel.isExistCustomer()) {
                if (!Objects.equals(confirmationViewModel.getCustomer().getName(), null)) {
                    customerName.setText(confirmationViewModel.getCustomer().getName());
                    customerName.setDisable(true);
                }
                if (!Objects.equals(confirmationViewModel.getCustomer().getDob(), null)) {
                    dob.setValue(DobFormatter.toDate(confirmationViewModel.getCustomer().getDob()));
                    dob.setDisable(true);
                }
            } else {
                if (customerName.isDisable()) {
                    customerName.setDisable(false);
                    customerName.setText("");
                }
                if (dob.isDisable()) {
                    dob.setDisable(false);
                }
            }

        });
        customerName.textProperty().addListener((obs, oldVal, newVal) -> confirmationViewModel.getCustomer().setName(newVal));
        money.setPromptText(NumberFormat.getNumberInstance(Locale.US).format(createOrderViewModel.getTotal().intValue()));
        money.setText(NumberFormat.getNumberInstance(Locale.US).format(createOrderViewModel.getTotal().intValue()));
        money.textProperty().addListener(((observableValue, oldVal, newVal) -> {
            String val = newVal;
            if (!newVal.matches("\\d*")) {
                val = newVal.replaceAll("\\D", "");
            }
            if (!val.isEmpty()) money.setText(NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(val)));
            else money.setText(val);
        }));
        dob.valueProperty().addListener(((observableValue, localDate, t1) -> {
            System.out.println("changed: " + t1);
            confirmationViewModel.getCustomer().setDob(DobFormatter.toString(t1));
        }));
        Router.setData(Pages.CONFIRMATION_VIEW, confirmationViewModel);
    }

    @FXML
    public void close() {
        Router.closeDialog();
    }

    @FXML
    public void confirm() throws Exception {
        if (money.getText().isEmpty()) {
            if (!notification.isVisible()) notification.setVisible(true);
            return;
        }
        if (!notification.isVisible()) notification.setVisible(false);
        Router.closeDialog();
        confirmationViewModel.saveCustomer();
        confirmationViewModel.getData().put("money", money.getText().replace(",", ""));
        confirmationViewModel.getData().put("method", confirmationViewModel.getMethod());
        Router.switchTo(Pages.ORDER_BILL_VIEW);
    }

}
