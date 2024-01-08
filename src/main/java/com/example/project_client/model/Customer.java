package com.example.project_client.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Getter
public class Customer {
    private Integer id =0;
    private String phoneNumber = "";
    private String name = null;
    private String dob = null;

}
