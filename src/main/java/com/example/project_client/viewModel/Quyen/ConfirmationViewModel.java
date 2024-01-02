package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Customer;
import com.example.project_client.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfirmationViewModel {
    @Setter
    @Getter
    private Boolean method = true;
    @Getter
    private Boolean isCostumerExist = false;
    @Getter
    private Customer customer = new Customer();
    @Getter
    private final Map<String,Object> data = new HashMap<>();
    private final CustomerRepository customerRepository = new CustomerRepository();
    public boolean checkCustomer() throws IOException {
        isCostumerExist = customerRepository.checkCustomer(customer.getPhoneNumber());
      return isCostumerExist;
    }
    public void findCustomer() throws IOException {

       customer = customerRepository.getCustomer(customer.getPhoneNumber());
    }
    public void saveCustomer() throws Exception {
        customerRepository.saveCustomer(customer);
    }
}
