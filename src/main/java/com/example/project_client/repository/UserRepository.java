package com.example.project_client.repository;

import com.example.project_client.data.Api;
import com.example.project_client.data.JsonUtils;
import com.example.project_client.data.Request;
import com.example.project_client.model.User;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    // Retrieves a user with the given username
    public User getUser(String username)  {
        User user = new User();
        try {
             return user = JsonUtils.fromJson(Request.sendGetRequest(Api.userApi + "?username=" + username), User.class);
        } catch (Exception e){
            System.out.println("error: "+e);
        }
        return null;
    }


    // Retrieves a list of all users
    public List<User> getAllUsers() {
        try {
            return JsonUtils.fromJson(Request.sendGetRequest(Api.userApi + "/getAll"), new TypeReference<>() {
            });
        } catch (Exception e){
            System.out.println("error: "+e);
        }
        return null;

    }
}


