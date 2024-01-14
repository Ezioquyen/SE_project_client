package com.example.project_client.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Customer {
    private Integer id = 0;
    private String phoneNumber = "";
    private String name = "";
    private String dob = "";
    private Integer total = 0;
}
