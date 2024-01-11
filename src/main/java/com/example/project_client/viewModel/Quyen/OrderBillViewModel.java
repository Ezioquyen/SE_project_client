package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.OrderBill;
import com.example.project_client.repository.OrderBillRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderBillViewModel {
    private final OrderBillRepository orderBillRepository = new OrderBillRepository();
    @Getter
    private OrderBill data;
    public void initData(OrderBill orderBill) throws Exception {
        this.data = orderBill;
        ConfirmationViewModel confirmationViewModel = (ConfirmationViewModel) Router.getData(Pages.CONFIRMATION_VIEW);
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("ssmmHHddMMyyyy");
        data.setId("OB" + dateFormat.format(currentDate));
        data.setCustomerPhoneNumber(confirmationViewModel.getCustomer().getPhoneNumber().isEmpty()?"":confirmationViewModel.getCustomer().getPhoneNumber());
        data.setUserStaffId("USS32");
       orderBillRepository.saveOrderBillApi(data);
    }
}
