package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Customer;
import com.example.project_client.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfirmationViewModel {
    @Getter
    boolean existCustomer;
    @Setter
    @Getter
    private Boolean method = true;
    @Getter
    private Customer customer = new Customer();
    @Getter
    private final Map<String, Object> data = new HashMap<>();
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void findCustomer() throws IOException {
       if(customerRepository.checkCustomer(customer.getPhoneNumber())) {
            existCustomer = true;
            customer = customerRepository.getCustomer(customer.getPhoneNumber());
       }else existCustomer = false;
    }

    public void saveCustomer() throws Exception {
       customerRepository.saveCustomer(customer);
    }
}
