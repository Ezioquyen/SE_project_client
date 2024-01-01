package com.example.project_client.model;

import lombok.Data;

import java.util.Date;
@Data
public class Customer {
    private String phoneNumber;
    private String name;
    private Date dob;
}
