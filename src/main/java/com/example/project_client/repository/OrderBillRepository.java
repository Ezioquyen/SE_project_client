package com.example.project_client.repository;

import com.example.project_client.data.Api;
import com.example.project_client.data.JsonUtils;
import com.example.project_client.data.Request;


import java.io.IOException;
import java.util.Objects;


public class OrderBillRepository {
    public void saveOrderBillApi(Object data) throws Exception {
        Request.sendPostRequest(Api.orderBillApi+"/saveBill", Objects.requireNonNull(JsonUtils.toJson(data))) ;
    }
}
