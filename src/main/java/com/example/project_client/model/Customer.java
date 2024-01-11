package com.example.project_client.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Customer {
    private Integer id =0;
    private String phoneNumber = "";
    private String name = null;
    private String dob = null;
    private Integer total = 0;

}
