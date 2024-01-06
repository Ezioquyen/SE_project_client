package com.example.project_client.repository;

import com.example.project_client.data.Api;
import com.example.project_client.data.JsonUtils;
import com.example.project_client.data.Request;
import com.example.project_client.model.Customer;

import java.io.IOException;
import java.util.Objects;

public class CustomerRepository {
    public Boolean checkCustomer(String phone) throws IOException {
        return JsonUtils.fromJson(Request.sendGetRequest(Api.customerApi+"/check/"+phone), Boolean.class);
    }
    public Customer getCustomer(String phone) throws IOException {
        return JsonUtils.fromJson(Request.sendGetRequest(Api.customerApi+"/getByPhone?phoneNumber="+phone), Customer.class);
    }
    public void saveCustomer(Customer customer) throws Exception {
        Request.sendPostRequest(Api.customerApi+"/save", Objects.requireNonNull(JsonUtils.toJson(customer)));
    }
}
