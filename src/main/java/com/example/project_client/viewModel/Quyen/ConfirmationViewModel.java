package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Customer;
import com.example.project_client.repository.CustomerRepository;
import lombok.Getter;

import java.io.IOException;

public class ConfirmationViewModel {
    @Getter
    Customer customer = new Customer();
    private final CustomerRepository customerRepository = new CustomerRepository();
    public boolean checkCustomer() throws IOException {
        return customerRepository.checkCustomer(customer.getPhoneNumber());
    }
    public void findCustomer() throws IOException {
       customer = customerRepository.getCustomer(customer.getPhoneNumber());
    }
}
