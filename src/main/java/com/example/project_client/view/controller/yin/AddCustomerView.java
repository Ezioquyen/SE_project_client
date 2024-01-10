package com.example.project_client.view.controller.yin;

import com.example.project_client.model.Customer;
import com.example.project_client.repository.CustomerRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.DobFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class AddCustomerView {


    @FXML
    private DatePicker dob;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumField;

    @FXML
    private Button resetCustomerBtn;

    @FXML
    private Button saveCustomerBtn;

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }
    @FXML
    void cancel(ActionEvent event) throws IOException {
        Router.goTo(Pages.CUSTOMER_VIEW);
    }

    private CustomerRepository customerRepository = new CustomerRepository();

    private String getPhoneNumber() {
        return phoneNumField.getText();
    }

    private String getName() {
        return nameField.getText();
    }


    @FXML
    void saveCustomer(ActionEvent event) throws Exception {
        if (validatePhoneNumber(getPhoneNumber()) &&
                validateName(getName()) &&
                emptyValidation("dob", dob.getEditor().getText().isEmpty())) {
            Customer customer = new Customer();
            customer.setPhoneNumber(getPhoneNumber());
            customer.setName(getName());
// Assuming you have a method to get the selected date from the DatePicker
            LocalDate selectedDate = dob.getValue();

// Using DobFormatter to convert LocalDate to String
            String formattedDob = DobFormatter.toString(selectedDate);

// Set the formatted date of birth to the customer
            customer.setDob(formattedDob);
            try {
                customerRepository.saveCustomer(customer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            saveAlert(customer);

        } else {
            try {
                Customer customer = customerRepository.getCustomer(phoneNumField.getText());
                customer.setPhoneNumber(getPhoneNumber());
                customer.setName(getName());
                // Assuming you have a method to get the selected date from the DatePicker
                LocalDate selectedDate = dob.getValue();

// Using DobFormatter to convert LocalDate to String
                String formattedDob = DobFormatter.toString(selectedDate);
                customer.setDob(formattedDob);
                customerRepository.saveCustomer(customer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clearFields();

        }
    }


    private void clearFields() {
        phoneNumField.clear();
        nameField.clear();
        dob.getEditor().clear();
    }

    private void saveAlert(Customer customer) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + customer.getPhoneNumber() + " " + customer.getName() + " has been created.");
        alert.showAndWait();
    }


    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
            if (empty) {
                alert.setContentText("Please Enter " + field);
            }
            else {
                alert.setContentText("Please Enter Valid " + field);
            }

        alert.showAndWait();

    }

    public boolean validateName(String name) {
        if (name == null || name.trim().length() == 0 || name.equals("null") || !name.matches("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$")) {
            validationAlert("Name", false);
            return false;

        }
        return true;
    }
    boolean validatePhoneNumber(String phoneNumber) {
        // verify if phone has 10 digits and start with 0
        if (phoneNumber.length() != 10 || phoneNumber.charAt(0) != '0') {
            validationAlert("Phone Number", false);

            return false;
        }
        // verify if phone contains only number
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }



}





