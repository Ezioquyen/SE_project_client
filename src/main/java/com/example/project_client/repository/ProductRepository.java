package com.example.project_client.repository;

import com.example.project_client.data.Api;
import com.example.project_client.data.JsonUtils;
import com.example.project_client.data.Request;
import com.example.project_client.model.Product;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

public class ProductRepository {
    public List<Product> getProductsApi() throws IOException {
        return JsonUtils.fromJson(Request.sendGetRequest(Api.productApi+"/getAll"), new TypeReference<>() {
        });
    }
}
