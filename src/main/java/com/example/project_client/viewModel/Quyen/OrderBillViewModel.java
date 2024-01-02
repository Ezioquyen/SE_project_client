package com.example.project_client.viewModel.Quyen;
import com.example.project_client.model.Product;
import com.example.project_client.repository.OrderBillRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderBillViewModel {
    private final OrderBillRepository orderBillRepository = new OrderBillRepository();
    @Getter
    private final Map<String,Object> data = new HashMap<>();
    public void initData() throws Exception {
        CreateOrderViewModel createOrderViewModel = (CreateOrderViewModel) Router.getData(Pages.CREATE_ORDER_VIEW);
        ConfirmationViewModel confirmationViewModel = (ConfirmationViewModel) Router.getData(Pages.CONFIRMATION_VIEW);
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("ssmmHHddMMyyyy");
        data.put("id", dateFormat.format(currentDate));
        data.put("buyDate",LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        data.put("customerPhone",confirmationViewModel.getCustomer().getPhoneNumber().isEmpty()?"":confirmationViewModel.getCustomer().getPhoneNumber());
        data.put("userStaffId","USS32");
        data.put("total",createOrderViewModel.getTotal().getValue());
        data.put("received", confirmationViewModel.getData().get("money").toString());
        data.put("changeMoney", Integer.parseInt(confirmationViewModel.getData().get("money").toString()) - createOrderViewModel.getTotal().getValue());
        data.put("payMethod",confirmationViewModel.getData().get("method"));
        List<Map<String,Object>> products = new ArrayList<>();
       for(Map.Entry<Product, SimpleIntegerProperty> entry : createOrderViewModel.getCount().entrySet()){
           Map<String,Object> productCount = new HashMap<>();
            productCount.put("productId",entry.getKey().getId());
            productCount.put("count",createOrderViewModel.getCount().get(entry.getKey()).getValue());
            products.add(productCount);
       }
        data.put("products",products);
       orderBillRepository.saveOrderBillApi(data);
    }
}
