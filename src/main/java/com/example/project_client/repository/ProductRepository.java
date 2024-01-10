package com.example.project_client.repository;

import com.example.project_client.data.Api;
import com.example.project_client.data.JsonUtils;
import com.example.project_client.data.Request;
import com.example.project_client.model.Product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class ProductRepository {
    public static List<Product> getProductsApi() throws IOException {
        String jsonpObject = Request.sendGetRequest(Api.productApi+"/getAll");
        System.out.println(jsonpObject);
        return JsonUtils.fromJson(jsonpObject, new TypeReference<>() {
        });
    }
    public static void saveProduct(Product product) throws Exception {
        Request.sendPostRequest(Api.productApi+"/save", Objects.requireNonNull(JsonUtils.toJson(product)));
    }
    public static void updateProduct(Product product) throws Exception {
        Request.sendPutRequest(Api.productApi+"/update", Objects.requireNonNull(JsonUtils.toJson(product)));
    }
    public static void deleteProduct(String id) throws Exception {
        Request.sendDeleteRequest(Api.productApi+"/delete", id);
    }
}
