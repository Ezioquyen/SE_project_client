package com.example.project_client.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String phoneNumber = "";
    private String name = "";
    private String dob = "";

}
