package com.example.project_client.view.controller.yin;

import com.example.project_client.model.Customer;
import com.example.project_client.repository.CustomerRepository;
import com.example.project_client.router.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.project_client.router.Pages.ADD_CUSTOMER_VIEW;

public class CustomerView implements Initializable {
    @FXML
    private DatePicker dob;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumField;

    @FXML
    private TableColumn<Customer, String> col_dob;

    @FXML
    private TableColumn<Customer, Boolean> col_edit;

    @FXML
    private TableColumn<Customer, String> col_name;

    @FXML
    private TableColumn<Customer, Integer> col_ID;

    @FXML
    private TableColumn<Customer, String> col_phoneNumber;


    @FXML
    private Button resetCustomerBtn;

    @FXML
    private Button saveCustomerBtn;

    @FXML
    private TableView<Customer> customerTable;
    private CustomerRepository customerRepository = new CustomerRepository();

    private ObservableList<Customer> customersList = FXCollections.observableArrayList();
    Customer customer = null;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("load");
            loadCustomerData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initializeColumns();

    }

    private void initializeColumns() {
        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Assuming col_dob is TableColumn<Customer, String> and dob is a Date property
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_edit.setCellFactory(createEditButtonCellFactory());
    }
    private Callback<TableColumn<Customer, Boolean>, TableCell<Customer, Boolean>> createEditButtonCellFactory() {
        return new Callback<TableColumn<Customer, Boolean>, TableCell<Customer, Boolean>>() {
            @Override
            public TableCell<Customer, Boolean> call(final TableColumn<Customer, Boolean> param) {
                return new TableCell<Customer, Boolean>() {

                    private final Button editButton = new Button(); // You can use any control here

                    {
               //      // Customize the Edit button with FontAwesome icon
               //      FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
               //      editIcon.setStyle("-fx-fill: #00E676; -fx-font-size: 18px;");
               //      editButton.setGraphic(editIcon);

                        // Set the action for the Edit button
                        editButton.setOnMouseClicked(event -> {
                            // Handle edit action for the selected row
                            Customer selectedCustomer = getTableView().getItems().get(getIndex());
                            System.out.println("customer: " + selectedCustomer.getName());

                            try {
                                handleEditAction(selectedCustomer);
                               // handleDeleteAction(selectedCustomer);
                               // loadCustomerData();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editButton);
                        }
                    }
                };
            }
        };
    }


 private void handleEditAction(Customer selectedCustomer) throws IOException {

//     AddCustomerView addCustomerView = (AddCustomerView) Router.getData(Pages.ADD_CUSTOMER_VIEW);
//
//     if (addCustomerView != null) {
//         // Pass the selectedCustomer information to the AddCustomerView
//         addCustomerView.updateCustomerInfo(selectedCustomer);
//         Router.goTo(Pages.ADD_CUSTOMER_VIEW);
//
//         System.out.println("Selected customer: " + selectedCustomer);
//         System.out.println("Phone number: " + selectedCustomer.getPhoneNumber());
//
//         // Navigate to the AddCustomerView
//
//     } else {
//         System.err.println("AddCustomerView not found in Router data.");
//     }
     AddCustomerView.setSelectCustomer(selectedCustomer);
     Router.switchTo(ADD_CUSTOMER_VIEW);
 }


    private void handleDeleteAction(Customer customer) throws IOException {
        customerRepository.deleteCustomer(Long.valueOf(customer.getId()));
    }
    private void loadCustomerData() throws IOException {
        List<Customer> lists = customerRepository.getCustomersApi();
        System.out.println(lists);

        if (lists != null) {
            customersList = FXCollections.observableArrayList(lists);
            customerTable.setItems(customersList);
        } else {
            System.out.println("Error loading customer data: List is null");
        }
    }


    @FXML
    void HandleTableSelected(MouseEvent event) {

    }


    @FXML
    void addCustomer(ActionEvent event) throws IOException {
       Router.switchTo(ADD_CUSTOMER_VIEW);
       // System.out.println(AddCustomerView.getSelectCustomer().toString());
    }


}