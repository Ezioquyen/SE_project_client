package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.Customer;
import com.example.project_client.model.OrderBill;
import com.example.project_client.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

public class ConfirmationViewModel {
    @Getter
    boolean existCustomer;
    @Setter
    @Getter
    private Customer customer = new Customer();
    private Integer deductionDob = 0;
    private Integer deductionTotal = 0;
    @Getter
    @Setter
    private  OrderBill orderBill;
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void findCustomer() throws IOException {
        if (customerRepository.checkCustomer(customer.getPhoneNumber())) {
            existCustomer = true;
            customer = customerRepository.getCustomer(customer.getPhoneNumber());
        } else existCustomer = false;
    }

    public void saveCustomer() throws Exception {
        customerRepository.saveCustomer(customer);
    }

    public Boolean checkDob(LocalDate localDate) {

        if (Objects.equals(localDate.format(DateTimeFormatter.ofPattern("dd-MM")), LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM")))) {
            addDobDeduction();

            return true;

        } else {
            subDobDeduction();
            deductionDob = 0;

            return false;

        }
    }

    public Boolean checkTotal() {

        Integer totalCheck = 500000;
        if(customer.getTotal() > totalCheck){
            addTotalDeduction();

            return true;
        }else {
            subTotalDeduction();
            deductionTotal = 0;

            return false;
        }
    }

   private void addDobDeduction() {
        deductionDob = orderBill.getOriginal() * 10 / 100;
        orderBill.setDeduction(orderBill.getDeduction() + deductionDob);
        orderBill.setTotal(orderBill.getOriginal() - orderBill.getDeduction());
    }

    private void addTotalDeduction() {
        deductionTotal =  orderBill.getOriginal() * 10 / 100;
        orderBill.setDeduction(orderBill.getDeduction() + deductionTotal);
        orderBill.setTotal(orderBill.getOriginal() - orderBill.getDeduction());
    }

    public void subDobDeduction() {
        orderBill.setDeduction(orderBill.getDeduction() - deductionDob);
        orderBill.setTotal(orderBill.getOriginal() - orderBill.getDeduction());
    }

    public void subTotalDeduction() {
        orderBill.setDeduction(orderBill.getDeduction() - deductionTotal);
        orderBill.setTotal(orderBill.getOriginal() - orderBill.getDeduction());
    }
}
